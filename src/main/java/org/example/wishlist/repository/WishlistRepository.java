package org.example.wishlist.repository;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.model.WishListRowMapper;
import org.example.wishlist.model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;


    public WishlistRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
        );
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Wishlist> getAllWishList() {
        String sql = "SELECT WISHLIST_ID, NAME, DESCRIPTION FROM wishlist";
        return jdbcTemplate.query(sql, new WishListRowMapper());
    }


    public void addWishlist(Wishlist wishlist, int userID) {
        String sql = "INSERT INTO WISHLIST (USER_ID, NAME, DESCRIPTION) VALUES (?,?,?)";
        jdbcTemplate.update(sql, userID, wishlist.getWishlistName(), wishlist.getWishlistDesc());
    }


    public String deleteWishlist(int wishlistID) {
        String sql = "DELETE FROM wishlist WHERE wishlist_ID = ?";
        jdbcTemplate.update(sql, wishlistID);
        return "delete success";
    }


    public void updateWishlist(String name, Wishlist newWishlist) { //String id i stedet for name?
        String sql = "UPDATE wishlist SET wish = ?, wishlist = ?, price = ?, wishlistDesc = ?, wishlistName = ? WHERE id = ?";
        jdbcTemplate.update(sql, newWishlist.getWishlist(), newWishlist.getPrice(), newWishlist.getWishlistDesc(),
                newWishlist.getWishlistName()); //Skal der ikke være et ID her?? //Hvorfor er der et wish i wishlist og en liste af wishes?

    }
}
