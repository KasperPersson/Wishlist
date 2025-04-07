package org.example.wishlist.repository;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.model.WishListRowMapper;
import org.example.wishlist.model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository implements IWishlist<Wishlist> {

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


    public void deleteWishlist(Wishlist wishlist) {

        String deleteWishesSql = "DELETE FROM wish WHERE wishlist_ID = ?";
        jdbcTemplate.update(deleteWishesSql, wishlist.getWishlistID());

        String sql = "DELETE FROM wishlist WHERE wishlist_ID = ?";
        jdbcTemplate.update(sql, wishlist.getWishlistID());
    }


    public void updateWishlist(Wishlist newWishlist) { //String id i stedet for name?
        String sql = "UPDATE wishlist SET wish = ?, wishlist = ?, price = ?, wishlistDesc = ?, wishlistName = ? WHERE id = ?";
        jdbcTemplate.update(sql, newWishlist.getWishlist(), newWishlist.getPrice(), newWishlist.getWishlistDesc(),
                newWishlist.getWishlistName()); //Skal der ikke være et ID her?? //Hvorfor er der et wish i wishlist og en liste af wishes?

    }

    public Wishlist getWishlistById(int wishlistId) {
        String sql = "SELECT wishlist_id, user_id, name, description FROM wishlist WHERE wishlist_id = ?";
        return jdbcTemplate.queryForObject(sql, new WishListRowMapper(), wishlistId);
    }

    @Override
    public void create(Wishlist wishlist) {

    }

    @Override
    public List<Wishlist> readAll() {
        String sql = "SELECT WISHLIST_ID, NAME, DESCRIPTION FROM wishlist";
        return jdbcTemplate.query(sql, new WishListRowMapper());
    }

    @Override
    public Wishlist read(int id) {
        return null;
    }

    @Override
    public void update(Wishlist wishlist) {

    }

    @Override
    public void delete(int id) {

    }
}
