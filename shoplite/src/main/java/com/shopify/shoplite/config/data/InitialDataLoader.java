package com.shopify.shoplite.config.data;

import com.shopify.shoplite.dao.CustomerService;
import com.shopify.shoplite.dao.InventoryService;
import com.shopify.shoplite.dao.TransactionService;
import com.shopify.shoplite.entities.Customer;
import com.shopify.shoplite.entities.Inventory;
import com.shopify.shoplite.entities.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Profile("default")
public class InitialDataLoader {

    private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    private final CustomerService customerService;

    private final InventoryService inventoryService;

    private final TransactionService transactionService;

    public InitialDataLoader(CustomerService customerService, InventoryService inventoryService, TransactionService transactionService) {
        this.customerService = customerService;
        this.inventoryService = inventoryService;
        this.transactionService = transactionService;
    }

    @Bean
    CommandLineRunner initDatabase() {
        Customer customer1 = new Customer();
        customer1.setName("John Smith");
        customer1.setAddress("The University of Manchester, Oxford Rd, Manchester M13 9PL");
        Inventory inventory1 = new Inventory();
        inventory1.setName("Laptop");
        inventory1.setDescription("MacBook Pro M1 Max");
        inventory1.setQuantity(10);
        Transaction transaction1 = new Transaction();
        transaction1.setCustomer(customer1);
        transaction1.setInventory(inventory1);
        transaction1.setQuantity(5);
        transaction1.setCreatedAt(LocalDateTime.now());
        transaction1.setStatus("Pending");

        return (args) -> {
            if ((customerService.count() > 0) && (inventoryService.count() > 0) && (transactionService.count() > 0)) {
                log.info("Database already populated with data. Skipping initialization.");
            } else {
                log.info("Initializing database with:" + customerService.save(customer1));
                log.info("Initializing database with:" + inventoryService.save(inventory1));
                log.info("Initializing database with:" + transactionService.save(transaction1));
            }
        };
    }
}
