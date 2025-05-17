package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.constant.Account;
import lombok.Builder;
import lombok.Data;

import java.time.YearMonth;
import java.util.Map;

@Data
@Builder
public class OdionMonthlyAccountTransactionResponse {

    private Map<Account, Map<YearMonth, Double>> accountMonthTransaction;
}
