package com.example.BarokTest;

import com.example.BarokTest.entity.Wallet;
import com.example.BarokTest.model.WalletModel;
import com.example.BarokTest.repository.WalletRepository;
import com.example.BarokTest.service.WalletService;
import com.example.BarokTest.service.WalletServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
public class WalletServiceTest {
    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletServiceImpl walletService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_update_balance_by_userId_positive_amount_should_increase_balance() {

        int userId = 1;
        long initialBalance = 100;
        long amountToAdd = 50;

        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(initialBalance);

        when(walletRepository.findByUserId(userId)).thenReturn(wallet);

        walletService.increase(userId, amountToAdd);

        assertEquals(initialBalance + amountToAdd, wallet.getBalance());
    }

    @Test
    public void test_update_balance_by_userId_negative_amount_should_decrease_balance() {

        Wallet wallet = new Wallet();
        wallet.setUserId(1);
        wallet.setBalance(100);
        when(walletRepository.findByUserId(1)).thenReturn(wallet);


        walletService.increase(1, -50);


        assertEquals(50, wallet.getBalance());
    }

    @Test
    public void test_save_wallet_balance() {

                Wallet wallet = Wallet.builder()
                .userId(1)
                .balance(150)
                .walletId(12)
                .build();

        Wallet saveWallet = walletRepository.save(wallet);
        Assertions.assertNotNull(wallet);
    }

    @Test
    public void test_get_wallet_balance() {
        int userId = 1;
        long expectedBalance = 100;

        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(expectedBalance);

        when(walletRepository.findByUserId(userId)).thenReturn(wallet);


        WalletModel walletModel = walletService.getWalletByUserId(userId);


        assertEquals(expectedBalance, walletModel.getBalance());
    }
}
