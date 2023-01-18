package com.alok.home.commons.repository;

import com.alok.home.commons.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    @Query("SELECT i FROM Investment i WHERE i.yearx = ?1 and i.monthx = ?2")
    List<Investment> findAllByYearMonth(Integer year, Integer month);
}

