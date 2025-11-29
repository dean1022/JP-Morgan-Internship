package com.jpmc.midascore.repository;

import com.jpmc.midascore.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {
    TransactionRecord findById(long id);
}