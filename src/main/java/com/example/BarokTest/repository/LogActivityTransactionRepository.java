package com.example.BarokTest.repository;

import com.example.BarokTest.entity.LogActivityTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface LogActivityTransactionRepository extends CrudRepository<LogActivityTransaction,Integer> {
}
