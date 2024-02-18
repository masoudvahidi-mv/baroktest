package com.example.BarokTest.repository;

import com.example.BarokTest.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface WalletRepository extends CrudRepository<Wallet,Integer> {

    boolean existsByUserId(int user_id);
    Wallet findByUserId(int user_id);
}
