package org.example.wishlist.model;

public class Wish {

    private String wishName;
    private int wishID;
    private String description;
    private int price;
    private String link;

    public Wish(String name, int wishID, String description, int price, String link) {
        this.wishName = name;
        this.wishID = wishID;
        this.description = description;
        this.price = price;
        this.link = link;
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

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public int getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
