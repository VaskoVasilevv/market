package com.example.market.web;

import com.example.market.model.DTO.ContractDto;
import com.example.market.model.entity.Contract;
import com.example.market.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<ContractDto>> getAllItems() {
        List<Contract> allIContracts = contractService.getAllContractsByStatus();

        List<ContractDto> allContractsDto = allIContracts.stream().map(i -> modelMapper.map(i,ContractDto.class)).collect(Collectors.toList());

        return ResponseEntity.
                ok(allContractsDto);
    }
}
