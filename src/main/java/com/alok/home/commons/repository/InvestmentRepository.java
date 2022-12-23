package com.alok.home.commons.repository;

import com.alok.home.commons.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}

