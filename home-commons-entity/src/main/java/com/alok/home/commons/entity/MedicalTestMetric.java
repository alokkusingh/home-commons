package com.alok.home.commons.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medical_test_metrics")
public class MedicalTestMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metric_id")
    private Integer metricId;

    @Column(name = "metric_name", nullable = false, unique = true, length = 100)
    private String metricName;

    @Column(name = "unit", length = 30)
    private String unit;

    @Column(name = "min_normal_value", precision = 10, scale = 3)
    private BigDecimal minNormalValue;

    @Column(name = "max_normal_value", precision = 10, scale = 3)
    private BigDecimal maxNormalValue;

    // Default Constructor
    public MedicalTestMetric() {
    }

    // Getters and Setters
    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getMinNormalValue() {
        return minNormalValue;
    }

    public void setMinNormalValue(BigDecimal minNormalValue) {
        this.minNormalValue = minNormalValue;
    }

    public BigDecimal getMaxNormalValue() {
        return maxNormalValue;
    }

    public void setMaxNormalValue(BigDecimal maxNormalValue) {
        this.maxNormalValue = maxNormalValue;
    }
}