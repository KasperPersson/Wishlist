package org.example.wishlist.service;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.repository.WishRepository;
import org.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;
    private final WishlistRepository wishlistRepository;

    public WishService(WishRepository wishRepository, WishlistRepository wishlistRepository) {
        this.wishRepository = wishRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getAllWishLists() {
        return wishlistRepository.getAllWishList();
    }

    public void updateWish(Wish wish) {
        wishRepository.updateWish(wish);
    }

    public void updateWishlist(String name, Wishlist wishlist){
        wishlistRepository.updateWishlist(name, wishlist);
    }
}
