package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.CustomerService;
import com.shopify.shoplite.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getPageData(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers/index";
    }

    @GetMapping("/{id}")
    public String getInventory(@PathVariable("id") long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/detail";
    }
}
