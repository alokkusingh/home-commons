package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.entity.Investment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RawInvestmentsResponse {
    List<Investment> investments;
}
