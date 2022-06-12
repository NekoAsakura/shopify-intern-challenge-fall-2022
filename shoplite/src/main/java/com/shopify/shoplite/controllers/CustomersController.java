package com.shopify.shoplite.controllers;

import com.shopify.shoplite.dao.CustomerService;
import com.shopify.shoplite.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @GetMapping("/new")
    public String newCustomer(Model model) {
        if (!model.containsAttribute("customer")) {
            model.addAttribute("customer", new Customer());
        }
        return "customers/new";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createCustomer(@RequestBody @Valid @ModelAttribute Customer customer, Model model, BindingResult errors,
                                 RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()) {
            model.addAttribute("customer", customer);
            return "customers/new";
        }
        if (customer.getName() == null || customer.getName().isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "Customer name is required");
            return "redirect:/customers";
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "Customer address is required");
            return "redirect:/customers";
        }
        redirectAttrs.addFlashAttribute("ok_message", "Customer has been added.");
        customerService.save(customer);
        return "redirect:/customers";
    }
}
