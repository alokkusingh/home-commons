package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.constant.Account;
import com.alok.home.commons.constant.AccountHead;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class OdionAccountsBalanceResponse {

    private List<AccountBalance> accountBalances;
    private Map<AccountHead, List<AccountBalance>> headAccountBalances;

    @Data
    @Builder
    public static class AccountBalance {
        private Account account;
        private Double balance;
    }
}
