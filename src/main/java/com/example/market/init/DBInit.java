package com.example.market.init;

import com.example.market.service.ContractService;
import com.example.market.service.ItemService;
import com.example.market.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final ItemService itemService;
    private final ContractService contractService;

    public DBInit(UserService userService, ItemService itemService, ContractService contractService) {
        this.userService = userService;
        this.itemService = itemService;
        this.contractService = contractService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsers();
        itemService.initItems();
        contractService.initContract();
    }
}
