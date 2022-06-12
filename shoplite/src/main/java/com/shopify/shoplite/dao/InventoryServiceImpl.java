package com.shopify.shoplite.dao;

import com.shopify.shoplite.entities.Inventory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public long count() {
        return inventoryRepository.count();
    }

    @Override
    public Optional<Inventory> findById(long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
