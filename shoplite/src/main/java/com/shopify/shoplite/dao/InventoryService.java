package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Inventory;

import java.util.Optional;

public interface InventoryService {
    long count();

    Optional<Inventory> findById(long id);

    Inventory save(Inventory inventory);
}
