package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ExpensesResponse {

    private List<Expense> expenses;
    private Integer count;
    private Date lastTransactionDate;

    @Data
    @Builder
    public static class Expense {
        private Long id;
        private Date date;
        private String head;
        private Double amount;
        private String category;
        private String comment;
    }
}
