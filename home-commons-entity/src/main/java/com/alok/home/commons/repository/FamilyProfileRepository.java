package com.alok.home.commons.repository;

import com.alok.home.commons.entity.FamilyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FamilyProfileRepository extends JpaRepository<FamilyProfile, Long> {
    Optional<FamilyProfile> findByName(String name);

    // Eagerly loads all family profiles and deep-fetches their linked lifecycle timeline events
    @Query("SELECT DISTINCT p FROM FamilyProfile p LEFT JOIN FETCH p.events ORDER BY p.name ASC")
    List<FamilyProfile> findAllWithEventsEagerly();
}
