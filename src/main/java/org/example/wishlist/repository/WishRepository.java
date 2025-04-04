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

    public List<Wish> getAllWishes() {
        String sql = "SELECT name, description, price, link FROM wish";
        return jdbcTemplate.query(sql, new WishRowMapper());
    }

    public List<Wish> getWishById(int wishlistId) {
        String sql = "SELECT wishlist_id, name, description, pris, link FROM wish WHERE wishlist_id = ?";
        return jdbcTemplate.query(sql, new WishRowMapper(), wishlistId);
    }

    public void addWish(Wish wish) {
        String sql = "INSERT INTO wish(name, description, price, link) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, wish.getWishName(), wish.getDescription(), wish.getPrice(), wish.getLink());
    }

    public void addWish(Wish wish, int wishlistId) {
        String sql = "INSERT INTO WISH (WISHLIST_ID, NAME, DESCRIPTION, LINK, PRIS) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlistId, wish.getWishName(), wish.getDescription(), wish.getLink(), wish.getPrice());
    } //TODO SKAL RYKKES TIL WISHLIST WISHLIST



    public String deleteWish(int wishID) {
        String sql = "DELETE FROM wish WHERE WISH_ID = ?";
        jdbcTemplate.update(sql, wishID);
        return "delete success";
    }


    public void updateWish(Wish wish) {
        String sql = "UPDATE wish SET wishName = ?, description = ?, price = ?, link = ? WHERE id = ?";
        jdbcTemplate.update(sql, wish.getWishName(), wish.getDescription(), wish.getPrice(), wish.getLink()); //wish.getId()) burde den ikke være der?
    }


}
