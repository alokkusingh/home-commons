package com.alok.home.commons.repository;

import com.alok.home.commons.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.head IS NOT NULL")
    List<Transaction> findAll();

    @Query("SELECT t FROM Transaction t WHERE t.head = 'Salary'")
    List<Transaction> findSalaryTransactions();

    @Query(value = "SELECT MAX(DATE) FROM transaction", nativeQuery = true)
    Optional<Date>  findLastTransactionDate();
}
