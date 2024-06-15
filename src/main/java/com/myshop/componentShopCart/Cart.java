package com.myshop.componentShopCart;

import com.myshop.model.Item;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(item))
        );
        recalculatePriceAndCounter();
    }
//        boolean notFound = true;
//
//        for (CartItem cartItem: cartItems) {
//            if (cartItem.getItem().getId().equals(item.getId())){
//                notFound = false;
//                cartItem.increaseCounter();
//                recalculatePriceAndCounter();
//                break;
//            }
//        }
//
//        if(notFound) {
//            cartItems.add(new CartItem(item));
//            recalculatePriceAndCounter();
//        }
//    }

    public void decreaseItem(Item item) {
        Optional<CartItem> oCartItem = getCartItemByItem(item);
        if(oCartItem.isPresent()){
            CartItem cartItem = oCartItem.get();
            cartItem.decreaseCounter();
            if(cartItem.hasZeroItems()) {
                removeAllItems(item);
            } else {
                recalculatePriceAndCounter();
            }
        }

    }

    public void removeAllItems(Item item) {
        cartItems.removeIf(i -> i.idEquals(item));
        recalculatePriceAndCounter();
    }
//        for(CartItem cartItem: cartItems) {
//            if(cartItem.getItem().getId().equals(item.getId())){
//                cartItem.decreaseCounter();
//                if(cartItem.hasZeroItems()){
//                    cartItems.remove(cartItem);
//                }
//                recalculatePriceAndCounter();
//                break;
//            }
//        }


    private void recalculatePriceAndCounter() {
        sum = cartItems.stream().map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        counter = cartItems.stream().mapToInt(CartItem::getCounter)
                .reduce(0, Integer::sum);

//        int tempCounter = 0;
//        BigDecimal tempPrice = BigDecimal.ZERO;
//
//        for(CartItem cartItem: cartItems){
//            tempCounter += cartItem.getCounter();
//            tempPrice = tempPrice.add(cartItem.getPrice());
//        }
//        this.counter = tempCounter;
//        this.sum = tempPrice;
    }

    private Optional<CartItem> getCartItemByItem(Item item) {
        return cartItems.stream()
                .filter(i -> i.idEquals(item))
                .findFirst();
    }

    public void clearCart() {
        cartItems.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }

}
