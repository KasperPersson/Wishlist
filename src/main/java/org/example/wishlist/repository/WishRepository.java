package org.example.wishlist.repository;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;


    public WishRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
        );
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Wish> getAllWishesById(int wishlistId) {
        String sql = "SELECT wish_id, wishlist_id, name, description, pris, link FROM wish WHERE wishlist_id = ?";
        return jdbcTemplate.query(sql, new WishRowMapper(), wishlistId);
    }

    public Wish getSpecificWishById(int wishId) {
        String sql = "SELECT wish_id, wishlist_id, name, description, pris, link FROM wish WHERE wish_id = ?";
        return jdbcTemplate.queryForObject(sql, new WishRowMapper(), wishId);
    }

    public void addWish(Wish wish) {
        String sql = "INSERT INTO wish(name, description, pris, link, wishlist_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, wish.getWishName(), wish.getDescription(), wish.getPrice(), wish.getLink(), wish.getWishlistId());
    }


    public void deleteWish(int id) {
        String sql = "DELETE FROM wish WHERE WISH_ID = ?";
        jdbcTemplate.update(sql, id);
    }


    public void updateWish(Wish wish) {
        String sql = "UPDATE wish SET name = ?, description = ?, pris = ?, link = ? WHERE WISH_ID = ?";
        jdbcTemplate.update(sql, wish.getWishName(), wish.getDescription(), wish.getPrice(), wish.getLink(), wish.getWishID()); //wish.getId()) burde den ikke være der?
    }


}
