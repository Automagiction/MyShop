package com.myshop.service;

import com.myshop.model.Item;
import com.myshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final ItemRepository itemRepository;

    @Autowired
    public AdminService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {

        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    }


    public Item updateItem(Long itemId, Item item) {
        Item itemById = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        itemById.setName(item.getName());
        itemById.setPrice(item.getPrice());
        itemById.setImgUrl(item.getImgUrl());
        return itemRepository.save(itemById);
    }
//    public Item editItem(Item item) {
//        return itemRepository.save(item);
//    }
}
