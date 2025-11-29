package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionListener {

    private static final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    private final TransactionService transactionService;

    public TransactionListener(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core")
    public void onTransaction(Transaction transaction) {
        // Put your BREAKPOINT on this line
        logger.info("Received transaction: {}", transaction);
        transactionService.processIncomingTransaction(transaction);
    }
}
