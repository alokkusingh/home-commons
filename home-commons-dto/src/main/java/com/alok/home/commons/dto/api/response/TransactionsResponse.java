package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TransactionsResponse {

    private List<Transaction> transactions;
    private Integer count;
    private Date lastTransactionDate;

    @Data
    @Builder
    public static class Transaction {
        private Integer id;
        private Date date;
        private Integer debit;
        private Integer credit;
        private String head;
        private String subHead;
        private String bank;
        private String description;
    }
}
