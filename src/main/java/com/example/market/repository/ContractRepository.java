package com.example.market.repository;

import com.example.market.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

    @Query(value = "SELECT * FROM market.contracts AS c WHERE c.active = true",nativeQuery = true)
    List<Contract> getAllActive();
}
