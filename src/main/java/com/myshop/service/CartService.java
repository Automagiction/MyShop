package com.myshop.service;

import com.myshop.componentShopCart.Cart;
import com.myshop.model.Item;
import com.myshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

//    public void addItemToCart(Long itemId) {
//        Optional<Item> oItem = itemRepository.findById(itemId);
//        if(oItem.isPresent()){
//            Item item = oItem.get();
//            cart.addItem(item);
//        }
//    }
//    public void decreaseItem(Long itemId) {
//        Optional<Item> oItem = itemRepository.findById(itemId);
//        if(oItem.isPresent()){
//            Item item = oItem.get();
//            cart.decreaseItem(item);
//        }
//    }
//    public void removeItem(Long itemId){
//        Optional<Item> oItem = itemRepository.findById(itemId);
//        if(oItem.isPresent()){
//            Item item = oItem.get();
//            cart.removeAllItems(item);
//        }
//    }

    public void itemOperation(Long itemId, ItemOperation itemOperation) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            switch (itemOperation) {
                case INCREASE -> cart.addItem(item);
                case DECREASE -> cart.decreaseItem(item);
                case REMOVE -> cart.removeAllItems(item);
                default -> throw new IllegalArgumentException();
            }
        }
    }
}
