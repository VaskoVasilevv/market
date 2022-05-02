package com.example.market.service.impl;

import com.example.market.model.entity.Item;
import com.example.market.model.entity.User;
import com.example.market.repository.ItemRepository;
import com.example.market.repository.UserRepository;
import com.example.market.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    private Item testItem;
    private ItemRepository mockItemRepository;
    private UserRepository mockUserRepository;
    private ItemService itemService;

    @BeforeEach
    void setUp() {

//      ARRANGE
        User user = new User();
        user.setId(1L);
        user.setUsername("User");
        user.setAccount(10);

        testItem = new Item();
        testItem.setName("Item1");
        testItem.setId(1L);
        testItem.setOwner(user);

        this.mockItemRepository = Mockito.mock(ItemRepository.class);
        this.mockUserRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    public void testGetAllItems_ShouldReturnCorrect(){
//      ARRANGE
        Mockito.when(this.mockItemRepository.getAllItems()).thenReturn(List.of(this.testItem));

        this.itemService = new ItemServiceImpl(this.mockItemRepository,this.mockUserRepository);

//      ACT
        List<Item> allItems = this.itemService.getAllItems();

//      ASSERT
        assertEquals(1,allItems.size());
    }

    @Test
    public void test(){
//      ARRANGE
        Mockito.when(this.mockItemRepository.getAllByOwnerId(1L)).thenReturn(List.of(this.testItem));

        this.itemService = new ItemServiceImpl(this.mockItemRepository,this.mockUserRepository);
//      ACT
        List<Item> allItemsByOwnerId = itemService.getAllItemsByOwnerId(1L);

//      ASSERT
        assertEquals(1,allItemsByOwnerId.size());

    }
}