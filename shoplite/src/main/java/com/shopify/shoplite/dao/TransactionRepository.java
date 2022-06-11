package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
