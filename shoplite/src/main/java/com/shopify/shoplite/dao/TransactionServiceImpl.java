package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public long count() {
        return transactionRepository.count();
    }

    @Override
    public Iterable<Transaction> findAll() {
        final Sort ASC_DATE = Sort.by("createdAt").descending();
        return transactionRepository.findAll(ASC_DATE);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Iterable<Transaction> findRecentTransactions() {
        final Sort ASC_DATE = Sort.by("createdAt").descending();
        Iterable<Transaction> transactions = transactionRepository.findAll(ASC_DATE);
        ArrayList<Transaction> recentTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (recentTransactions.size() < 5 && !transaction.getStatus().equals("Cancelled")) {
                recentTransactions.add(transaction);
            }
        }
        return recentTransactions;
    }
}
