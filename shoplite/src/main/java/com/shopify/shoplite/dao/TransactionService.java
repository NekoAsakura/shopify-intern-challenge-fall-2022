package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Transaction;

public interface TransactionService {
    long count();

    Iterable<Transaction> findAll();

    Transaction findById(Long id);

    void deleteById(Long id);

    Transaction save(Transaction transaction);

    Iterable<Transaction> findRecentTransactions();
}
