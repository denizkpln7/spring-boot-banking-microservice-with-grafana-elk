package com.denizkpln.transactionserver.repository;

import com.denizkpln.transactionserver.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
