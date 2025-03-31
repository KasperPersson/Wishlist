package org.example.wishlist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("wishlist_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        int price = rs.getInt("price");
        String link = rs.getString("link");

        return new Wish(name, id, description, price, link);
    }
}