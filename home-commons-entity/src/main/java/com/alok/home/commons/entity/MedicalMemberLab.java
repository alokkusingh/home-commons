package com.alok.home.commons.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "medical_member_labs")
public class MedicalMemberLab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer labRecordId;

    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    @OneToMany(mappedBy = "memberLab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalLabResult> results = new ArrayList<>();

    // Getters, Setters, and helper to chain values
    public void addResult(MedicalLabResult result) {
        results.add(result);
        result.setMemberLab(this);
    }
}
