package com.alok.home.commons.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TaxMonthly {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Short yearx;
    private Short monthx;
    private Integer paidAmount;
}
