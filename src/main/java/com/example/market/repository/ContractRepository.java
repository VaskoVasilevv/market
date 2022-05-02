package com.example.market.repository;

import com.example.market.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

    @Query(value = "SELECT * FROM market.contracts AS c WHERE c.active = :status",nativeQuery = true)
    List<Contract> getAllByStatus(@PathVariable("status") boolean status);


    @Query("SELECT c from Contract c where c.id = :id")
    Contract getContractById(@Param("id") Long id);

//    Bonus get all contracts by seller id
    @Query(value = "select * from market.contracts as c where c.seller_id = :id",nativeQuery = true)
    List<Contract> getAllContractsBySellerId(@PathVariable("id") Long id);
}
