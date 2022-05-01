package com.example.market.service;


import com.example.market.model.entity.Contract;

import java.util.List;

public interface ContractService {

    void initContract();

    List<Contract> getAllContractsByStatus();

    Contract getContractById(Long id);

    Contract update(Long id, double price);

    void close();

    List<Contract> getContractBySeller(Long id);

    Contract deal();
}
