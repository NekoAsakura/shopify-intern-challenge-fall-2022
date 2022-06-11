package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
