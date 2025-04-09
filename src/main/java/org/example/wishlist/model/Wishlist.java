package org.example.wishlist.model;

import java.util.ArrayList;

public class Wishlist {
    private int wishlistID;
    private ArrayList<Wish> wishlist;
    private String wishlistDesc;
    private String wishlistName;

    public Wishlist() {

    }

    public Wishlist(int wishlistID, ArrayList<Wish> wishlist, String wishlistDesc, String name) {
        this.wishlistID = wishlistID;
        this.wishlist = wishlist;
        this.wishlistDesc = wishlistDesc;
        this.wishlistName = name;
    }



    public ArrayList<Wish> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<Wish> wishlist) {
        this.wishlist = wishlist;
    }


    public String getWishlistName() {
        return wishlistName;
    }


    public String getWishlistDesc() {
        return wishlistDesc;
    }


    public int getWishlistID() {
        return wishlistID;
    }

    //bruges til unit test
    public void setWishlistID(int wishlistID){
        this.wishlistID = wishlistID;
    }

}
