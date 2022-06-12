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
        customer1.setName("Neko Asakura");
        customer1.setAddress("The University of Manchester, Oxford Rd, Manchester M13 9PL");
        Inventory inventory1 = new Inventory();
        inventory1.setName("MacBook Air 13.3\" (2020)");
        inventory1.setDescription("macOS 11.0 Big Sur\nApple M1 chip\nRAM: 8 GB / Storage: 256 GB SSD\nRetina display\nBattery life: Up to 18 hours");
        inventory1.setQuantity(10);
        Inventory inventory2 = new Inventory();
        inventory2.setName("iPhone 13 Pro Max");
        inventory2.setDescription("6.7\" Super Retina XDR display with ProMotion\nDolby Vision HDR video recording up to 4K at 60 fps\nA15 Bionic chip with New 16-core Neural Engine\n Up to 28 hours video playback");
        inventory2.setQuantity(50);
        Transaction transaction1 = new Transaction();
        transaction1.setCustomer(customer1);
        transaction1.setInventory(inventory1);
        transaction1.setQuantity(5);
        transaction1.setCreatedAt(LocalDateTime.now().minusDays(15));
        transaction1.setStatus("Delivered");
        Transaction transaction2 = new Transaction();
        transaction2.setCustomer(customer1);
        transaction2.setInventory(inventory2);
        transaction2.setQuantity(10);
        transaction2.setCreatedAt(LocalDateTime.now());
        transaction2.setStatus("Pending");

        return (args) -> {
            if ((customerService.count() > 0) && (inventoryService.count() > 0) && (transactionService.count() > 0)) {
                log.info("Database already populated with data. Skipping initialization.");
            } else {
                log.info("Initializing database with:" + customerService.save(customer1));
                log.info("Initializing database with:" + inventoryService.save(inventory1));
                log.info("Initializing database with:" + transactionService.save(transaction1));
                log.info("Initializing database with:" + inventoryService.save(inventory2));
                log.info("Initializing database with:" + transactionService.save(transaction2));
            }
        };
    }
}
