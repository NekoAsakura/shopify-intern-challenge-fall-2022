package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TxnController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String getPageData(Model model) {
        model.addAttribute("txns", transactionService.findAll());
        return "transactions/index";
    }
}
