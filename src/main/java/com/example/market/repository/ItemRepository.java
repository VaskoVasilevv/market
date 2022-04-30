package com.example.market.repository;

import com.example.market.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Item getItemById(@Param("id") Long id);

    @Query("SELECT i FROM Item i WHERE i.owner.id = 1")
    List<Item> getAllItems();
}
