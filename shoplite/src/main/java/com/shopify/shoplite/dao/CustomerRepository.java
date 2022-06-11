package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
