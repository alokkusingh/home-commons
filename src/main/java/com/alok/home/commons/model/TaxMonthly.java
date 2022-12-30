package com.alok.home.commons.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.YearMonth;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TaxMonthly implements Comparable<TaxMonthly> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private YearMonth yearMonth;
    private Integer paidAmount;

    @Override
    public int compareTo(TaxMonthly o) {
        return this.yearMonth.compareTo(o.yearMonth);
    }

}
