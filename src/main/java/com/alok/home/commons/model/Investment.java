package com.alok.home.commons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Investment implements Comparable<Investment> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Short yearx;
    private Short monthx;
    private String head;
    private Integer contribution;
    private Integer valueAsOnMonth;

    public Integer getContribution() {
        return contribution==null?0:contribution;
    }

    public Integer getValueAsOnMonth() {
        return valueAsOnMonth==null?0:valueAsOnMonth;
    }

    @Override
    public int compareTo(@NotNull Investment o) {
        if (this.getYearx() > o.getYearx())
            return 1;

        if (this.getYearx() < o.getYearx())
            return -1;

        if (this.getMonthx() > o.getMonthx())
            return 1;

        if (this.getMonthx() < o.getMonthx())
            return -1;

        return 0;
    }
}
