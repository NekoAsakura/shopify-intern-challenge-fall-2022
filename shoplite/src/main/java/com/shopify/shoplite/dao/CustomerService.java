package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Customer;

public interface CustomerService {
    long count();

    Iterable<Customer> findAll();

    Customer findById(long id);

    Customer save(Customer customer);
}
