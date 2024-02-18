package com.example.BarokTest.service;

import com.example.BarokTest.entity.LogActivityTransaction;
import com.example.BarokTest.model.DepositRequest;
import com.example.BarokTest.repository.LogActivityTransactionRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LogActivityTransactionImpl implements LogActivityTransactionService {

    @Autowired
    private LogActivityTransactionRepository logActivityTransactionRepository;

    @Override
    public void saveLog(DepositRequest request, String type, String reference) {
        LogActivityTransaction log = LogActivityTransaction.builder()
                .amount(request.getAmount())
                .userId(request.getUser_id())
                .transactionId(reference)
                .type(type)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        logActivityTransactionRepository.save(log);
    }

    @Override
    public long totalAmount() {
        List<LogActivityTransaction> totallTransaction = (List<LogActivityTransaction>) logActivityTransactionRepository.findAll();
        long totalAmount = totallTransaction.stream().mapToLong(t->t.getAmount()).sum();
        return totalAmount;
    }

}
