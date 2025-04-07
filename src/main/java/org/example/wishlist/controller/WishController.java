package org.example.wishlist.controller;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/wishlists")
    public String getAllWishLists(Model model) {
        List<Wishlist> wishLists = wishService.getAllWishLists();
        model.addAttribute("wishLists", wishLists);
        return "wishlist";
    }

    @GetMapping("/wishlists/{id}")
    public String viewWishlist(@PathVariable int id, Model model) {
        Wishlist wishlist = wishService.getWishlistById(id);
        List<Wish> wishes = wishService.getAllWishesById(id);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishes);

        for (Wish wish : wishes) {
            System.out.println("Wish ID: " + wish.getWishID());
        }

        return "wishlist-details";
    }

    //henter layout for specifict ønske
    @GetMapping("/wishes/{id}")
    public String viewWish (@PathVariable int id, Model model){
        Wish wish = wishService.getSpecificWishById(id);
        model.addAttribute("wish", wish);

        return "wish-details";
    }

    //henter layout for redigering af ønske
    @GetMapping("/wishes/{id}/edit")
    public String editWish(@PathVariable int id, Model model){
        Wish wish = wishService.getSpecificWishById(id);
        model.addAttribute("wish", wish);

        return "edit-wish";
    }

    //updatere ønsket
    @PostMapping("/update")
    public String updateWish(@ModelAttribute("wish") Wish wish) {
        wishService.updateWish(wish);
        return "redirect:/wishes/" + wish.getWishID();
    }
}
