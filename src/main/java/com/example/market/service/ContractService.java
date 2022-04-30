package com.example.market.service;


import com.example.market.model.entity.Contract;

import java.util.List;

public interface ContractService {

    void initContract();

    List<Contract> getAllContractsByStatus();
}
