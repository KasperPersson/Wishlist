package org.example.wishlist.controller;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.service.WishService;
import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("")
    public String index(){
    return "index";
    }

    @GetMapping("/wishlists")
    public String getAllWishLists(Model model) {
        List<Wishlist> wishLists = wishService.getAllWishLists();
        model.addAttribute("wishLists", wishLists);
        return "wishlist";
    }

    @PostMapping("/update")
    public String editWish(@ModelAttribute("wish") Wish wish){
        wishService.updateWish(wish);
        return "redirect:/wishes";
    }
}
