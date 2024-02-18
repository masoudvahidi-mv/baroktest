package com.example.BarokTest.service;

import com.example.BarokTest.entity.Wallet;
import com.example.BarokTest.model.DepositRequest;
import com.example.BarokTest.model.WalletModel;

public interface WalletService {

    WalletModel getWalletByUserId(int user_id);
    boolean isExistWallet(int user_id );
    WalletModel createWallet(DepositRequest request);
    Wallet increase(int user_id,long amount);
}
