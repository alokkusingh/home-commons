package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MonthlySummaryResponse {

    private List<MonthlySummary> records;
    private Integer count;

    @Data
    @Builder
    public static class MonthlySummary {
        private Integer year;
        private Integer month;
        private Double incomeAmount;
        private Double expenseAmount;
        private Double transferAmount;
        private Double savingAmount;
        private Long investmentAmount;
        private Long investmentByCompany;
        private Long taxAmount;
        private Long ctc;
    }
}
