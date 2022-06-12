package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.InventoryRepository;
import com.shopify.shoplite.entities.Inventory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        model.addAttribute("inventory", inventory);
        return "inventory/detail";
    }

    @GetMapping("/new")
    public String newInventory(Model model) {
        if (!model.containsAttribute("inventory")) {
            model.addAttribute("inventory", new Inventory());
        }
        return "inventory/new";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createInventory(@RequestBody @Valid @ModelAttribute Inventory inventory, BindingResult errors, Model model, RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()) {
            model.addAttribute("inventory", inventory);
            return "inventory/new";
        }
        if (inventory.getName() == null || inventory.getName().isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "Inventory name is required");
            return "redirect:/inventory";
        }
        if (inventory.getQuantity() <= 0) {
            redirectAttrs.addFlashAttribute("error", "Quantity must be greater than 0");
            return "redirect:/inventory";
        }
        redirectAttrs.addFlashAttribute("ok_message", "Inventory added.");
        inventoryRepository.save(inventory);
        return "redirect:/inventory";
    }

    @GetMapping("/edit/{id}")
    public String editInventory(@PathVariable("id") long id, Model model) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        model.addAttribute("inventory", inventory);
        return "inventory/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateInventory(@RequestBody @Valid @ModelAttribute Inventory inventory, BindingResult errors, Model model, RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()) {
            model.addAttribute("inventory", inventory);
            return "inventory/edit";
        }
        if (inventory.getName() == null || inventory.getName().isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "Inventory name is required");
            return "redirect:/inventory";
        }
        if (inventory.getQuantity() <= 0) {
            redirectAttrs.addFlashAttribute("error", "Quantity must be greater than 0");
            return "redirect:/inventory";
        }
        redirectAttrs.addFlashAttribute("ok_message", "Inventory updated.");
        inventoryRepository.save(inventory);
        return "redirect:/inventory";
    }
}
