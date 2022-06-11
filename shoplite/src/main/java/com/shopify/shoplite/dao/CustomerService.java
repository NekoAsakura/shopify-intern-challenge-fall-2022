package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Customer;

public interface CustomerService {
    long count();

    Customer save(Customer customer);
}
