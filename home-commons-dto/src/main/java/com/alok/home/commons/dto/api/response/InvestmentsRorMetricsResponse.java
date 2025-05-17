package com.alok.home.commons.dto.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InvestmentsRorMetricsResponse {

    private List<InvestmentsRorMetric> investmentsRorMetrics;

    @Builder
    @Data
    public static class InvestmentsRorMetric {
        private String metric;
        private InvestmentsReturn PF;
        private InvestmentsReturn NPS;
        private InvestmentsReturn LIC;
        private InvestmentsReturn SHARE;
        private InvestmentsReturn MF;
        private InvestmentsReturn total;


        @Builder
        @Data
        public static class InvestmentsReturn {
            private Integer beg;
            private Integer end;
            private Integer inv;
            private Double ror;
        }
    }


}
