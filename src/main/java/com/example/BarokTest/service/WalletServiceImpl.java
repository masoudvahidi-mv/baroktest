package com.example.BarokTest.service;

import com.example.BarokTest.entity.Wallet;
import com.example.BarokTest.model.DepositRequest;
import com.example.BarokTest.model.WalletModel;
import com.example.BarokTest.repository.WalletRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WalletServiceImpl implements WalletService {

   
 @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
    }

    @Override
    public WalletModel getWalletByUserId(int user_id) {

        Wallet wallet = walletRepository.findByUserId(user_id);

        WalletModel walletModel = new WalletModel();
        BeanUtils.copyProperties(wallet,walletModel);

        return walletModel;
    }

    @Override
    public boolean isExistWallet(int user_id) {
        return  walletRepository.existsByUserId(user_id);
    }

    @Override
    public WalletModel createWallet(DepositRequest request) {

        Wallet wallet =
                Wallet.builder()
                .userId(request.getUser_id())
                .balance(request.getAmount())
                .createdat(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        walletRepository.save(wallet);

        WalletModel walletModel = new WalletModel();
        BeanUtils.copyProperties(wallet,walletModel);

        return walletModel;
    }

    @Override
    // If the amount is negative, we decrease the balance by the absolute value of the amount

    // man ba in kar mokhalefam 
    // do model tarif koni behtar nist> deposit - withrawal maslan? b jayi k dargir manfi mosbat bshei>??
    public Wallet increase(int user_id, long amount) {

        Wallet wallet = walletRepository.findByUserId(user_id);
        wallet.setBalance(calculate(wallet.getBalance(),amount));
        wallet.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return walletRepository.save(wallet);
    }


    public long calculate(long balance, long amount) {
        if(amount > 0)
            return balance + amount;
        else
            return balance - Math.abs(amount);
    }

}
