package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Transaction;

public interface TransactionService {
    long count();

    public Iterable<Transaction> findAll();

    public Transaction findById(Long id);

    public void deleteById(Long id);

    public Transaction save(Transaction transaction);

    public Iterable<Transaction> findRecentTransactions();
}
