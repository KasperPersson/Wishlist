package org.example.wishlist.repository;


import org.example.wishlist.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    public UserRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
        );
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    }

    List<User> users = new ArrayList<>(List.of(new User("Admin", "1234")));

    public User getUser(String uid) {
        for (User user : users) {
            if (user.getUid().equals(uid))
                return user;
        }
        return null;
    }
}
