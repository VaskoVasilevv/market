package com.example.market.service;

import com.example.market.model.entity.Item;

import java.util.List;

public interface ItemService {

    void initItems();

    List<Item> getAllItems();

    List<Item> getAllItemsByOwnerId(Long id);
}
