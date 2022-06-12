package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.InventoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
