package com.alok.home.commons.repository;

import com.alok.home.commons.entity.MedicalMemberLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalMemberLabRepository extends JpaRepository<MedicalMemberLab, Integer> {

    // Used to clear out previous data blocks during an ETL refresh loop
    void deleteByEntityId(Long entityId);

    // Pulls historical lab entries sorted by latest date first for your Web UI timeline
    List<MedicalMemberLab> findByEntityIdOrderByTestDateDesc(Long entityId);
}
