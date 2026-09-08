package com.alok.home.commons.repository;

import com.alok.home.commons.entity.MedicalTestMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MedicalTestMetricRepository extends JpaRepository<MedicalTestMetric, Integer> {
    Optional<MedicalTestMetric> findByMetricName(String metricName);
}
