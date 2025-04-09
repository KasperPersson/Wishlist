package org.example.wishlist.repository;

import org.example.wishlist.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        return jdbcTemplate.query(sql, new WishlistRowMapper());
    }


    public int addWishlist(Wishlist wishlist) {
        String sql = "INSERT INTO WISHLIST (NAME, DESCRIPTION) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishlist.getWishlistName());
            ps.setString(2, wishlist.getWishlistDesc());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();

    }


    public void deleteWishlist(Wishlist wishlist) {

        String deleteWishesSql = "DELETE FROM wish WHERE wishlist_ID = ?";
        jdbcTemplate.update(deleteWishesSql, wishlist.getWishlistID());

        String sql = "DELETE FROM wishlist WHERE wishlist_ID = ?";
        jdbcTemplate.update(sql, wishlist.getWishlistID());
    }



    public void updateWishlist(Wishlist wishlist) {
        String sql = "UPDATE wishlist SET NAME = ?, DESCRIPTION = ? WHERE WISHLIST_ID = ?";
        jdbcTemplate.update(sql, wishlist.getWishlistName(), wishlist.getWishlistDesc(), wishlist.getWishlistID());
    }

    public Wishlist getWishlistById(int wishlistId) {
        String sql = "SELECT wishlist_id, user_id, name, description FROM wishlist WHERE wishlist_id = ?";
        return jdbcTemplate.queryForObject(sql, new WishlistRowMapper(), wishlistId);
    }


    @Override
    public void create(Wishlist wishlist) {

    }

    @Override
    public List<Wishlist> readAll() {
        String sql = "SELECT WISHLIST_ID, NAME, DESCRIPTION FROM wishlist";
        return jdbcTemplate.query(sql, new WishlistRowMapper());
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
