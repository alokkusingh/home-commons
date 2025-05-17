package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.entity.IExpenseMonthSum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExpensesMonthSumResponse {

    private List<IExpenseMonthSum> expenseCategorySums;
    private Integer count;
}
