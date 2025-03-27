package org.example.wishlist.model;

import java.util.ArrayList;

public class Wishlist {
    private String wishlistName;
    private int price;
    private ArrayList<Wish> wishlist;
    private Wish wish;

    public Wishlist(Wish wish, ArrayList<Wish> wishlist, int price, String name) {
        this.wish = wish;
        this.wishlist = wishlist;
        this.price = price;
        this.wishlistName = name;
    }

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    public ArrayList<Wish> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<Wish> wishlist) {
        this.wishlist = wishlist;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }
}
