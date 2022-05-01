package com.example.market.web;

import com.example.market.model.DTO.ItemDto;
import com.example.market.model.entity.Item;
import com.example.market.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

//  URL - /items
    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<Item> allItems = itemService.getAllItems();

        List<ItemDto> allItemsDTO = mapItemToItemDto(allItems);

        return ResponseEntity.
                ok(allItemsDTO);
    }

//  Bonus All items by owner id!
//  URL - /items/1
    @GetMapping("{id}")
    public ResponseEntity<List<ItemDto>> getAllItemsByOwnerId(@PathVariable Long id) {

        List<Item> allItems = itemService.getAllItemsByOwnerId(id);

        List<ItemDto> allItemsDTO = mapItemToItemDto(allItems);

        return ResponseEntity.
                ok(allItemsDTO);
    }



    private List<ItemDto> mapItemToItemDto(List<Item> allItems) {
        return allItems.stream().map(i -> {

            ItemDto itemDTO = new ItemDto();
            itemDTO.setId(i.getId());
            itemDTO.setName(i.getName());
            itemDTO.setOwnerId(i.getOwner().getId());
            itemDTO.setOwnerUsername(i.getOwner().getUsername());

            return itemDTO;
        }).collect(Collectors.toList());
    }
}
