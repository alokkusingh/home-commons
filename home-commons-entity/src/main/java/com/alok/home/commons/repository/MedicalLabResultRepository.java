package com.alok.home.commons.repository;

import com.alok.home.commons.entity.MedicalLabResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // <-- Correct Import Line
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalLabResultRepository extends JpaRepository<MedicalLabResult, Long> {

    // Fast chronological trend fetcher optimized for charting single metrics over time
    @Query("SELECT r FROM MedicalLabResult r " +
            "JOIN r.memberLab ml " +
            "WHERE ml.entityId = :entityId AND r.metric.metricId = :metricId " +
            "ORDER BY ml.testDate ASC")
    List<MedicalLabResult> findHistoricalTrends(
            @Param("entityId") Long entityId,
            @Param("metricId") Integer metricId
    );
}
