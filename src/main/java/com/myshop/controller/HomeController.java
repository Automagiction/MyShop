package com.myshop.controller;


import com.myshop.componentShopCart.Cart;
import com.myshop.model.Item;
import com.myshop.repository.ItemRepository;
import com.myshop.service.CartService;
import com.myshop.service.ItemOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {

    private final CartService cartService;

    @Autowired
    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

//    @Autowired
//    public HomeController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    //    static List<Item> items = new ArrayList<>();
//    static {
//            items.add(new Item("Ołówek", new BigDecimal("2.50"), "https://e-sklep.koh-i-noor.pl/976-home_default/150000f01170p104-kin-olowek-grafitowy-1500-f-koh-i-noor-pojedynczy.jpg"));
//            items.add(new Item("Długopis", new BigDecimal("3.20"), "https://sklep.gumed.edu.pl/wp-content/uploads/2021/03/dlugopis-03-03_1-scaled.jpg"));
//            items.add(new Item("Pióro", new BigDecimal("6.70"), "https://a.allegroimg.com/original/119ea4/941e58af4d4bbd96e05c7c17f3ef/PIORO-WIECZNE-METALOWE-DLUGOPIS-NA-PREZENT-ELEGANT"));
//    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("items", cartService.getAllItems());
        return "home2";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model) {
        cartService.itemOperation(itemId, ItemOperation.INCREASE);
        model.addAttribute("items", cartService.getAllItems());
        return "home2";
    }
}
