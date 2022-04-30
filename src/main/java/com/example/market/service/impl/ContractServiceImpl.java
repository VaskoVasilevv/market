package com.example.market.service.impl;

import com.example.market.model.entity.Contract;
import com.example.market.model.entity.Item;
import com.example.market.model.entity.User;
import com.example.market.repository.ContractRepository;
import com.example.market.repository.ItemRepository;
import com.example.market.repository.UserRepository;
import com.example.market.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ContractServiceImpl(ContractRepository contractRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public void initContract() {
        if (contractRepository.count() == 0){

            Contract contract = new Contract();
            User seller = userRepository.getUserById(1L);
            User buyer = userRepository.getUserById(2L);
            Item item = itemRepository.getItemById(3L);

            contract.setPrice(100);
            contract.setActive(true);
            contract.setSeller(seller);
            contract.setBuyer(buyer);
            contract.setItem(item);

            contractRepository.save(contract);
        }
    }

    @Override
    public List<Contract> getAllContractsByStatus() {
        List<Contract> allActive = contractRepository.getAllActive();

        return allActive;

    }
}
