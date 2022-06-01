package com.example.market.web;

import com.example.market.model.DTO.ContractClosedDto;
import com.example.market.model.DTO.ContractDto;
import com.example.market.model.entity.Contract;
import com.example.market.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;
    private final ModelMapper modelMapper;

    public ContractController(ContractService contractService, ModelMapper modelMapper) {
        this.contractService = contractService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public ResponseEntity<List<ContractDto>> getAllContractsByStatus() {
        List<Contract> allIContracts = contractService.getAllContractsByStatus(true);

        List<ContractDto> allContractsDto = allIContracts.stream().map(i -> modelMapper.map(i, ContractDto.class)).collect(Collectors.toList());

        return ResponseEntity.
                ok(allContractsDto);

    }

    @GetMapping("all")
    public ResponseEntity<List<ContractClosedDto>> getAllClosedContracts() {
        List<Contract> allIContracts = contractService.getAllContractsByStatus(false);

        List<ContractClosedDto> allContractsDto = allIContracts.stream().map(i -> modelMapper.map(i, ContractClosedDto.class)).collect(Collectors.toList());

        return ResponseEntity.
                ok(allContractsDto);
    }

    //  URL - /contracts/update/4
    //  Contract id is 4. With this endpoint we are updating the price of contract.
    @GetMapping("/update/{id}")
    public ResponseEntity<ContractDto> updateActiveContract(@PathVariable Long id) {

        Contract contract = contractService.update(id, 200.0);

        ContractDto contractDto = modelMapper.map(contract, ContractDto.class);

        return ResponseEntity.ok(contractDto);

    }

    //  URL - /contracts/close
    //  With this endpoint we are closing the contract and set his status to false, and transfer money from buyer to seller!
    @GetMapping("/close")
    public ResponseEntity<ContractDto> closing() {

        contractService.close();

        Contract contractById = contractService.getContractById(4L);

        return ResponseEntity.ok(modelMapper.map(contractById, ContractDto.class));
    }

    //  URL - /contracts/all/1
//  Bonus! With this endpoint I represent all contracts by seller!
    @GetMapping("/all/{id}")
    public ResponseEntity<List<ContractDto>> allContractsBySellerId(@PathVariable("id") Long id) {

        List<Contract> contract = contractService.getContractBySellerId(id);

        List<ContractDto> contractDto = contract.stream().map(c -> modelMapper.map(c, ContractDto.class)).collect(Collectors.toList());

        return ResponseEntity.ok(contractDto);
    }

    //  Bonus part with currency (User2 has 200EUR in his bank account, he bought item3 for 50EUR. After deal User1 has 60USD in his bank account and User2 has 150EUR.
    //  If you use this endpoint, drop all tables for correct information.
    //  URL - /contracts/deal
    @GetMapping("/deal")
    public ResponseEntity<ContractDto> contractDeal() {

        Contract contract = contractService.deal();

        return ResponseEntity.ok(modelMapper.map(contract, ContractDto.class));

    }

}
