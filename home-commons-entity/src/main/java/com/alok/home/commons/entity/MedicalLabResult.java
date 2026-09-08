package com.alok.home.commons.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "medical_lab_results", schema = "home-stack")
public class MedicalLabResult {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long resultId;

        @ManyToOne @JoinColumn(name = "lab_record_id", nullable = false)
        private MedicalMemberLab memberLab;

        @ManyToOne @JoinColumn(name = "metric_id", nullable = false)
        private MedicalTestMetric metric;

        @Column(name = "recorded_value", nullable = false)
        private BigDecimal recordedValue;

        @Column(name = "is_out_of_range")
        private Boolean isOutOfRange;
}
