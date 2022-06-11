package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Inventory;

public interface InventoryService {
    long count();

    Inventory save(Inventory inventory);
}
