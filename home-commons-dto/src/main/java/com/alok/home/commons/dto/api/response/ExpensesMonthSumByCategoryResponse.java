package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExpensesMonthSumByCategoryResponse {

    private List<ExpenseCategoryMonthSum> expenseCategorySums;
    private Integer count;

    @Data
    @Builder
    public static class ExpenseCategoryMonthSum {
        Integer year;
        Integer month;
        String category;
        Double sum;

    }
}
