package org.example.wishlist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishListRowMapper implements RowMapper<Wishlist> {
    @Override
    public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Wishlist(
                rs.getInt("WISHLIST_ID"),
                new ArrayList<>(), // Ønsker hentes separat
                0, // Totalpris beregnes senere
                rs.getString("DESCRIPTION"),
                rs.getString("NAME")
        );
    }
}