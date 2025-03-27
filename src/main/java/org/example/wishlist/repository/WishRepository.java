package org.example.wishlist.repository;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class WishRepository {
private JdbcTemplate jdbcTemplate;


public WishRepository(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

public void getAllWishes(){

}

public void getAllWishlist(){

}

public void addWish(Wish wish){

}

public void addWishlist(Wishlist wishlist){

}

public void deleteWish (String name){

}

public void deleteWishlist (String name){

}

public void updateWish(String name, Wish newWish){

}

public void updateWishlist(String name, Wishlist newWishlist){

}





}
