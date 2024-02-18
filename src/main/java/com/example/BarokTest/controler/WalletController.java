package com.example.BarokTest.controler;

import com.example.BarokTest.entity.Wallet;
import com.example.BarokTest.exceptions.UserNotFound;
import com.example.BarokTest.model.DepositBalance;
import com.example.BarokTest.model.DepositRequest;
import com.example.BarokTest.model.DepositResponse;
import com.example.BarokTest.model.WalletModel;
import com.example.BarokTest.service.LogActivityTransactionService;
import com.example.BarokTest.service.WalletService;
import com.example.BarokTest.type.TypeLog;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final int MIN_REFERENCE =1;
    private final int MAX_REFERENCE =100000000;

    @Autowired
    private WalletService walletService;

    @Autowired
    private LogActivityTransactionService logActivityTransactionService;


    @PostMapping("/deposit")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<DepositResponse> deposit(@Valid @RequestBody DepositRequest depositRequest) {

        if ( walletService.isExistWallet(depositRequest.getUser_id()) ) {

            walletService.increase(depositRequest.getUser_id(),depositRequest.getAmount());
            String refrence = String.valueOf(generateRandomReference(MIN_REFERENCE,MAX_REFERENCE));
            DepositResponse response = new DepositResponse(refrence);

            saveLog(depositRequest,TypeLog.DEPOSIT.name(),refrence);

            return ResponseEntity.ok(response);
        }
        else {
            WalletModel walletModel = walletService.createWallet(depositRequest);
            String refrence = String.valueOf(generateRandomReference(MIN_REFERENCE,MAX_REFERENCE));
            DepositResponse response = new DepositResponse(refrence);

            saveLog(depositRequest, TypeLog.CREATE.name(),refrence);

            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<DepositBalance> getBalance(@PathVariable int user_id) {

        if ( walletService.isExistWallet(user_id) ) {
            WalletModel wallet = walletService.getWalletByUserId(user_id);
            DepositBalance balance = new DepositBalance(wallet.getBalance());

            return ResponseEntity.ok(balance);
        }

        throw new UserNotFound("User Not Exist");
    }

    public int generateRandomReference(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void saveLog(DepositRequest request, String type, String reference) {
        logActivityTransactionService.saveLog(request,type,reference);
    }

    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    public void totalAmountDaily() {
        double totalAmount = logActivityTransactionService.totalAmount();
        System.out.println("Total amount of transactions: " + totalAmount);
    }

}
