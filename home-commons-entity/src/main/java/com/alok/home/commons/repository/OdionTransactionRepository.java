package com.alok.home.commons.repository;

import com.alok.home.commons.constant.Account;
import com.alok.home.commons.entity.OdionTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OdionTransactionRepository extends JpaRepository<OdionTransaction, Integer> {

    @Query("SELECT ot FROM OdionTransaction ot WHERE ot.debitAccount = ?1 OR ot.creditAccount = ?1")
    List<OdionTransaction> findTransactionsForAccount(Account account);
}

