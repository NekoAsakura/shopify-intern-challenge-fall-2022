package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.CustomerService;
import com.shopify.shoplite.dao.InventoryService;
import com.shopify.shoplite.dao.TransactionService;
import com.shopify.shoplite.entities.Inventory;
import com.shopify.shoplite.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/transactions")
public class TxnController {
    private final TransactionService transactionService;

    private final InventoryService inventoryService;

    private final CustomerService customerService;

    public TxnController(TransactionService transactionService, InventoryService inventoryService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.inventoryService = inventoryService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getPageData(Model model) {
        model.addAttribute("txns", transactionService.findAll());
        return "transactions/index";
    }

    @GetMapping("/{id}")
    public String getTxn(@PathVariable("id") long id, Model model) {
        model.addAttribute("txn", transactionService.findById(id));
        return "transactions/detail";
    }

    @GetMapping("/new")
    public String newTxn(Model model) {
        if (!model.containsAttribute("txn")) {
            model.addAttribute("txn", new Transaction());
        }
        if (!model.containsAttribute("Inventory")) {
            model.addAttribute("inventory", inventoryService.findAll());
        }
        if (!model.containsAttribute("customers")) {
            model.addAttribute("customers", customerService.findAll());
        }
        return "transactions/new";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createTxn(@RequestBody @Valid @ModelAttribute Transaction txn, BindingResult errors,
                            Model model, RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()){
            model.addAttribute("txn", txn);
            model.addAttribute("inventory", inventoryService.findAll());
            model.addAttribute("customers", customerService.findAll());
            return "transactions/new";
        }
        if (txn.getQuantity() <= 0) {
            redirectAttrs.addFlashAttribute("error", "Quantity must be greater than 0");
            return "redirect:/transactions";
        }
        txn.setCreatedAt(LocalDateTime.now());
        Inventory stock = inventoryService.findById(txn.getInventory().getId()).orElse(null);
        if (txn.getQuantity() > stock.getQuantity()) {
            txn.setStatus("Pending");
            redirectAttrs.addFlashAttribute("error", "Not enough inventory! The transaction was pending.");
        } else {
            txn.setStatus("Processing");
            redirectAttrs.addFlashAttribute("ok_message", "New transaction added.");
            stock.setQuantity(stock.getQuantity() - txn.getQuantity());
        }
        transactionService.save(txn);
        return "redirect:/transactions";
    }
}
