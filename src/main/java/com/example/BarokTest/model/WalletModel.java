package com.example.BarokTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletModel {
    private int walletId;
    private int userId;
    private long balance;
}
