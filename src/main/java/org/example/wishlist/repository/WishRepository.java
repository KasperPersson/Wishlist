package org.example.wishlist.repository;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class WishRepository {
    private JdbcTemplate jdbcTemplate;


    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void getAllWishes() {

    }

    public void getAllWishlist() {

    }

    public void addWish(Wish wish) {

    }

    public void addWishlist(Wishlist wishlist) {

    }

    public String deleteWish(int wishID) {
        String sql = "DELETE FROM wish WHERE WISH_ID = ?";
        jdbcTemplate.update(sql, wishID);
        return "delete success";
    }

    public String deleteWishlist(int wishlistID) {
        String sql = "DELETE FROM wishlist WHERE wishlist_ID = ?";
        jdbcTemplate.update(sql, wishlistID);
        return "delete success";
    }

    public void updateWish(String name, Wish newWish) {

    }

    public void updateWishlist(String name, Wishlist newWishlist) {

    }


}
