package com.myshop.controller;

import com.myshop.model.Item;
import com.myshop.model.order.Order;
import com.myshop.repository.ItemRepository;
import com.myshop.repository.order.OrderRepository;
import com.myshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String DEFAULT_IMG_URL = "https://th.bing.com/th/id/R.c541cd4fe2434588cd0768490a9d1cb0?rik=SyUb%2byZJo5Rdmg&riu=http%3a%2f%2fsiku.pl%2fimg%2fBrak_obrazka.svg.png&ehk=R7ZKSNG%2bqJCwUcEXG6p5yb1%2fYuzYlQJXaNw%2faNHic7Y%3d&risl=&pid=ImgRaw&r=0";
    private final AdminService adminService;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(AdminService adminService, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.adminService = adminService;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    private String adminPage(Model model) {
        model.addAttribute("items", adminService.getAllItems());
        return "adminview/addItem";
    }

    @PostMapping("/add")
    private String addItem(Item item) {
        //HomeController.items.add(item);
        if (item.getImgUrl().isEmpty()){
            item.setImgUrl(DEFAULT_IMG_URL);
        }
        itemRepository.save(item);
        return "redirect:/admin";
    }

    @GetMapping("/remove/{itemId}")
    private String removeItem(@PathVariable("itemId") Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/admin";
    }

    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}
