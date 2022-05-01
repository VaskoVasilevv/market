package com.example.market.repository;

import com.example.market.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    //  JPQL Query
    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Item getItemById(@Param("id") Long id);


    //  JPQL Query
    @Query("SELECT i FROM Item i WHERE i.owner.id = 1")
    List<Item> getAllItems();


    //  BONUS Native Query
    @Query(value = "SELECT * FROM items WHERE items.owner_id = :id", nativeQuery = true)
    List<Item> getAllByOwnerId(@PathVariable("id") Long id);

}
