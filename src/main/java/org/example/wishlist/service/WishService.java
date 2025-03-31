package org.example.wishlist.service;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wishlist> getAllWishLists() {
        return wishRepository.getAllWishList();
    }

    public void updateWish(Wish wish) {
        wishRepository.updateWish(wish);
    }
}
