package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.InventoryRepository;
import com.shopify.shoplite.entities.Inventory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    public String getPageData(Model model) {
        model.addAttribute("inventory", inventoryRepository.findAll());
        return "inventory/index";
    }

    @GetMapping("/{id}")
    public String getInventory(@PathVariable("id") long id, Model model) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        model.addAttribute("inv", inventory);
        return "inventory/detail";
    }

}
