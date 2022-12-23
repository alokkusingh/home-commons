package com.alok.home.commons.repository;

import com.alok.home.commons.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}

