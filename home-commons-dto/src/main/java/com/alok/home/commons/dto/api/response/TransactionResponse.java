package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionResponse {

    private Integer id;
    private Date date;
    private Integer debit;
    private Integer credit;
    private String head;
    private String subHead;
    private String description;
    private String bank;

}
