package org.example.wishlist.model;

import java.util.ArrayList;

public class Wishlist {
    private int wishlistID;
    private ArrayList<Wish> wishlist;
    private int price;
    private String wishlistDesc;
    private String wishlistName;

    public Wishlist() {

    }

    public Wishlist(int wishlistID, ArrayList<Wish> wishlist, int price, String wishlistDesc, String name) {
        this.wishlistID = wishlistID;
        this.wishlist = wishlist;
        this.price = price;
        this.wishlistDesc = wishlistDesc;
        this.wishlistName = name;
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

    public String getWishlistDesc() {
        return wishlistDesc;
    }

    public void setWishlistDesc(String wishlistDesc) {
        this.wishlistDesc = wishlistDesc;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }
}
