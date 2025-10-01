package com.alok.home.commons.security;

import com.alok.home.commons.security.entity.CustomUserDetails;
import com.alok.home.commons.security.entity.UserInfo;
import com.alok.home.commons.security.properties.TokenIssuerProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Set;

@Slf4j
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Value("${application.id}")
    private String applicationId;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenIssuerProperties tokenIssuerProperties;

    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("localhost".equals(request.getHeader("host").split(":")[0])) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            "localhost",
                            null,
                            Set.of(new SimpleGrantedAuthority("ROLE_LOCALHOST"))
                    )
            );

            filterChain.doFilter(request, response);
            return;
        }

        // Take it from header
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        // If not in header, take it from request parameter
        // I know this is not a good practice as token will be in plain text in URL
        // And in OAuth2.1 spec (which is still in draft) passing token in URL is deprecated
        if (bearerToken == null) {
            bearerToken = request.getParameter("token");
        }

        String scope = null;
        // If not in request parameter, take it from cookie
        if (bearerToken == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("HOME_STACK_ACCESS_TOKEN")) {
                    bearerToken = "Bearer " + cookie.getValue();
                } else if (cookie.getName().equals("TOKEN_SCOPE")) {
                    scope = cookie.getValue();
                }
            }
        }

        String issuer = request.getHeader("issuer");
        issuer = issuer ==  null? "google": issuer;
        String clientId = request.getHeader("client-id");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!issuer.equals("google") && !"user".equals(scope) && clientId == null) {
            log.error("client-id is mandatory for non Google IdP token and non user scope");
            filterChain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        Request authRequest = null;
        if (issuer.equals("google")) {
            log.debug("Authenticating with google IdP");
            authRequest = new Request.Builder()
                    .url(tokenIssuerProperties.getUrls().get(issuer))
                    .method("POST", body)
                    .addHeader("Authorization", bearerToken)
                    .build();
        } else {
            log.debug("Authenticating with home-auth IdP");
            if ("user".equals(scope)) {
                log.debug("Authenticating for user scope");
                authRequest = new Request.Builder()
                        .url(tokenIssuerProperties.getUrls().get(issuer))
                        .method("POST", body)
                        .addHeader("Authorization", bearerToken)
                        .build();
            } else {
                log.debug("Authenticating for client scope");
                authRequest = new Request.Builder()
                        .url(tokenIssuerProperties.getUrls().get(issuer))
                        .method("POST", body)
                        .addHeader("Authorization", bearerToken)
                        .addHeader("subject", clientId)
                        .addHeader("audience", applicationId)
                        .build();
            }
        }


        UserInfo userInfo;
        try {
            Response authResponse = client.newCall(authRequest).execute();
            if (!authResponse.isSuccessful()) {
                log.error("Auh Service return code: {}", authResponse.code());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            userInfo = objectMapper.readValue(authResponse.body().string(), UserInfo.class);
            log.debug("Auth response: {}", userInfo);
        } catch (RuntimeException | ConnectException rte) {
            rte.printStackTrace();
            log.error("Error: Auth APi call failed");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        UserDetails userDetails = new CustomUserDetails(userInfo);
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
