package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Inventory;

import java.util.Optional;

public interface InventoryService {
    long count();

    Iterable<Inventory> findAll();

    Optional<Inventory> findById(long id);

    Inventory save(Inventory inventory);
}
