package com.alok.home.commons.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import com.alok.home.commons.security.dto.AuthResponseDto;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;


public class HomeAuthTokenInterceptor implements ClientHttpRequestInterceptor {

    private String tokenIssuer;
    private String tokenIssuerUrl;
    private String tokenScope;
    private String tokenAudience;
    private String clientId;
    private String applicationSecret;

    private final OkHttpClient client = new OkHttpClient().newBuilder()
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public HomeAuthTokenInterceptor(String tokenIssuer, String tokenIssuerUrl, String tokenScope, String tokenAudience, String clientId, String applicationSecret) {
        this.tokenIssuer = tokenIssuer;
        this.tokenIssuerUrl = tokenIssuerUrl;
        this.tokenScope = tokenScope;
        this.tokenAudience = tokenAudience;
        this.clientId = clientId;
        this.applicationSecret = applicationSecret;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Get jwt token
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody authBody = RequestBody.create(mediaType, "");
        Request authRequest = new Request.Builder()
                .url(tokenIssuerUrl)
                .method("POST", authBody)
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((clientId + ":" + applicationSecret).getBytes(StandardCharsets.UTF_8)))
                .addHeader("scope", tokenScope)
                .addHeader("audience", tokenAudience)
                .addHeader("grant-type", "client_credentials")
                .build();

        try {
            Response authResponse = client.newCall(authRequest).execute();
            if (authResponse.isSuccessful()) {
                AuthResponseDto authResponseDto = objectMapper.readValue(authResponse.body().string(), AuthResponseDto.class);
                var headers = request.getHeaders();
                headers.add("Authorization", "Bearer " + authResponseDto.accessToken());
                headers.add("issuer", tokenIssuer);
                headers.add("client-id", clientId);
            }
        } catch (RuntimeException | ConnectException rte) {
            rte.printStackTrace();
        }

        return execution.execute(request, body);
    }
}
