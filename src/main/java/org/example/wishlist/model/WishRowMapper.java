package org.example.wishlist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        int wishId = rs.getInt("wish_id");
        int wishlistId = rs.getInt("wishlist_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        int price = rs.getInt("pris");
        String link = rs.getString("link");

        return new Wish(name, wishId, description, price, link, wishlistId);
    }
}