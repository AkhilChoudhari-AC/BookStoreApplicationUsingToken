package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Ability to provide CURD operation and save our data to the database
@Repository
public interface OrderRepository extends JpaRepository<OrderData, Integer> {
}
