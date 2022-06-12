package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.TransactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/home", produces = {MediaType.TEXT_HTML_VALUE})
public class HomeController {
    private final TransactionService transactionService;

    public HomeController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    public String getPageData(Model model) {
        model.addAttribute("recTxn", transactionService.findRecentTransactions());
        return "home/index";
    }
}
