package com.example.BarokTest.service;

import com.example.BarokTest.model.DepositRequest;

public interface LogActivityTransactionService {
    void saveLog(DepositRequest request, String type, String reference);
    long totalAmount();
}
