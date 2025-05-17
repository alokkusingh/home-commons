package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.entity.OdionTransaction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OdionTransactionsResponse {

    private List<OdionTransaction> transactions;
}
