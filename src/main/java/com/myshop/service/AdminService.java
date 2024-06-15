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
}
