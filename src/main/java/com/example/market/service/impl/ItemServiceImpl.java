package com.example.market.service.impl;

import com.example.market.model.entity.Item;
import com.example.market.model.entity.User;
import com.example.market.repository.ItemRepository;
import com.example.market.repository.UserRepository;
import com.example.market.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void initItems() {
        if (itemRepository.count() == 0){
            Item item = new Item();

            User owner = userRepository.getUserById(1L);
            item.setName("Item1");
            item.setOwner(owner);

            itemRepository.save(item);
        }
    }

    @Override
    public List<Item> getAllItems() {

        List<Item> allItems = itemRepository.getAllItems();
        return allItems;
    }
}
