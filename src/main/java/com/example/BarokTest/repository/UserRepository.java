package com.example.BarokTest.repository;

import com.example.BarokTest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, GenericDao<User,Long> {
}
