package com.example.BarokTest.repository;

import com.example.BarokTest.entity.LogActivityTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//y soal age proje bozorg shod mikhay dar eza hame object ha ina ro benevisi>??
// b in fekr kon chetori mitoani GenralDao ina besazi k hame chi bran sar jashon b doni k injori bashi
public interface LogActivityTransactionRepository extends CrudRepository<LogActivityTransaction,Integer> {
}
