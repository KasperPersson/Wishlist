package org.example.wishlist.model;

public class Wish {

    private String wishName;
    private int wishID;
    private String description;
    private int price;
    private String link;
    private int wishlistId;

    public Wish() {

    }
    public Wish(String name, int wishID, String description, int price, String link, int wishlistId) {
        this.wishName = name;
        this.wishID = wishID;
        this.description = description;
        this.price = price;
        this.link = link;
        this.wishlistId = wishlistId;
    }

    public String getWishName() {
        return wishName;
    }

    public String getDescription() {
        return description;
    }

    public int getWishID() {
        return wishID;
    }


    public int getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setLink(String link) {
        this.link = link;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }
    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
