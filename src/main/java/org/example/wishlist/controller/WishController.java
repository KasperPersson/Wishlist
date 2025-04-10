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
        List<Wishlist> wishLists = wishService.getAllWishlists();
        model.addAttribute("wishLists", wishLists);
        return "wishlist";
    }

    @GetMapping("/wishlists/{id}")
    public String viewWishlist(@PathVariable int id, Model model) {
        Wishlist wishlist = wishService.getWishlistById(id);
        List<Wish> wishes = wishService.getAllWishesById(id);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishes);

        return "wishlist-details";
    }

    //henter layout for specifict ønske
    @GetMapping("/wishes/{id}")
    public String viewWish(@PathVariable int id, Model model) {
        Wish wish = wishService.getSpecificWishById(id);
        model.addAttribute("wish", wish);

        return "wish-details";
    }

    //henter layout for redigering af ønske
    @GetMapping("/wishes/{id}/edit")
    public String editWish(@PathVariable int id, Model model) {
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

    @GetMapping("/wishlists/{id}/add-wish")
    public String showAddWish(@PathVariable("id") int id, Model model){
        Wishlist wishlist = wishService.getWishlistById(id);
        model.addAttribute("wishlist", wishlist);
    model.addAttribute("wish", new Wish());
    return "add-wish";
    }

    @PostMapping("/wishlists/{id}/adding")
    public String addingWish(@PathVariable("id") int wishlistId, @ModelAttribute Wish wish){
        wish.setWishlistId(wishlistId);
        wishService.addWish(wish);

        return "redirect:/wishlists/" + wishlistId;
    }

    @PostMapping("/wishlists/delete/{id}")
    public String deleteWishList(@PathVariable int id) {
        Wishlist wishlist = wishService.getWishlistById(id);
        wishService.deleteWishlistById(wishlist);
        return "redirect:/wishlists";
    }

    @PostMapping("/wishes/delete/{id}")
    public String deleteWish(@PathVariable int id) {
        Wish wish = wishService.getSpecificWishById(id);
        wishService.deleteWishById(id);
        return "redirect:/wishlists/" + wish.getWishlistId();
    }

    //viser create formen til tilføjelse af ny ønskeliste
    @GetMapping("/wishlists/create")
    public String createWishlistForm(Model model) {
        model.addAttribute("wishlist", new Wishlist());
        return "wishlists-create";
    }

    //går ind på ønskelisten man lige har tilføjet, når man trykker gem
    @PostMapping("/wishlists/create")
    public String createWishlist(@ModelAttribute Wishlist wishlist){
        int newID = wishService.addWishlist(wishlist);
       return "redirect:/wishlists/" + newID;
    }

    @GetMapping("/wishlists/{id}/edit")
    public String editWishlistform(@PathVariable int id, Model model) {
        Wishlist wishlist = wishService.getWishlistById(id);
        model.addAttribute("wishlist", wishlist);

        return "edit-wishlist";
    }





    @PostMapping("/wishlists/edit")
    public String updateWishlist(@ModelAttribute("wishlist") Wishlist wishlist) {
        wishService.updateWishlist(wishlist);
        return "redirect:/wishlists/" + wishlist.getWishlistID();
    }



}
