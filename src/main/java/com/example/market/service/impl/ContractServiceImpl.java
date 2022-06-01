package com.example.market.service.impl;

import com.example.market.model.entity.Contract;
import com.example.market.model.entity.Item;
import com.example.market.model.entity.User;
import com.example.market.repository.ContractRepository;
import com.example.market.repository.ItemRepository;
import com.example.market.repository.UserRepository;
import com.example.market.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public ContractServiceImpl(ContractRepository contractRepository, UserRepository userRepository, ItemRepository itemRepository, ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initContract() {
        if (contractRepository.count() == 0) {

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
    public List<Contract> getAllContractsByStatus(boolean status) {

            return  contractRepository.getAllByStatus(status);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.getContractById(id);
    }


    @Override
    public Contract update(Long id, double price) {
        Contract contractById = contractRepository.getContractById(id);

        if (contractById.isActive()) {
            contractById.setPrice(price);
            contractRepository.save(contractById);
        }

        return contractById;
    }

    @Override
    public void close() {
        Contract contract = getAllContractsByStatus(true).stream().findFirst().orElse(null);

        if (contract != null) {
            User seller = contract.getSeller();
            User buyer = contract.getBuyer();

            if (buyer.getAccount() >= contract.getPrice()) {

                buyer.setAccount(buyer.getAccount() - contract.getPrice());
                seller.setAccount(seller.getAccount() + contract.getPrice());
                userRepository.save(buyer);
                userRepository.save(seller);

                Item item = itemRepository.getItemById(3L);
                item.setOwner(buyer);
                itemRepository.save(item);

                contract.setActive(false);
                contractRepository.save(contract);
            }
        }

    }

    @Override
    public List<Contract> getContractBySellerId(Long id) {

        return contractRepository.getAllContractsBySellerId(id);
    }

    @Override
    public Contract deal() {
        User seller = userRepository.getUserById(1L);
        User buyer = userRepository.getUserById(2L);
        Item itemById = itemRepository.getItemById(3L);

        Contract contract = new Contract();
        contract.setActive(true);
        contract.setSeller(seller);
        contract.setBuyer(buyer);
        contract.setPrice(60);
        contract.setItem(itemById);

        double buyerAccountInEUR = buyer.getAccount() * 1.2;

        if (buyerAccountInEUR >= contract.getPrice()) {
            itemById.setOwner(buyer);
            buyer.setAccount(buyer.getAccount() - Math.round(contract.getPrice() * 0.84));

            seller.setAccount(seller.getAccount() + contract.getPrice());

            userRepository.save(buyer);
            userRepository.save(seller);

            itemRepository.save(itemById);

            contract.setActive(false);
            contractRepository.save(contract);
        }

        return contract;

    }


}
