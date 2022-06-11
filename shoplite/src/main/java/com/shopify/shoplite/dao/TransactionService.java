package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Transaction;

public interface TransactionService {
    long count();
    Transaction save(Transaction transaction);
}
