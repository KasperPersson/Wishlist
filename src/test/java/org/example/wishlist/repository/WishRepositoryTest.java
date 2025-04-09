package org.example.wishlist.repository;

import org.example.wishlist.model.Wish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)

class WishRepositoryTest {

    @Autowired
    private WishRepository wishRepository;

    @Test
    void getAllWishesByWishListID() {
        //Arange
        Integer wishlistId = 2;

        //ACT
        List<Wish> wishes = wishRepository.getAllWishesById(wishlistId);

        //Assert
        Assertions.assertEquals(1, wishes.size());
    }

    @Test
    void getSpecificWishById() {

        //Arrange
        Integer wishId = 1;

        //ACT
        Wish wish = wishRepository.getSpecificWishById(wishId);

        //Assert
        Assertions.assertEquals("SKO", wish.getWishName());
        Assertions.assertEquals("STR. 38", wish.getDescription());
        Assertions.assertEquals("https://www.adidas.dk/handball-spezial-sko/BD7632.html", wish.getLink());
        Assertions.assertEquals(399, wish.getPrice());


    }

    @Test
    void addWish() {
        //Arrange
        Wish newWish = new Wish();
        newWish.setWishlistId(2);
        newWish.setWishName("Cykel");
        newWish.setDescription("Jeg er så glad for min cykel");
        newWish.setLink("https://www.jegErSåGladForMinCykel.dk/");
        newWish.setPrice(139000);

        //ACT
        wishRepository.addWish(newWish);

        //Assert
        List<Wish> wishes = wishRepository.getAllWishesById(2);
        Wish wish = wishes.get(wishes.size() -1);

        Assertions.assertEquals("Cykel", wish.getWishName());
        Assertions.assertEquals(139000, wish.getPrice());

    }

    @Test
    void deleteWish() {
        //Arange
        Integer wishId = 3;

        //ACT
        wishRepository.deleteWish(wishId);

        //ASSERT
        Wish deletedWish = null;
        try {
            deletedWish = wishRepository.getSpecificWishById(wishId);
        } catch (EmptyResultDataAccessException e) {

        }
        Assertions.assertNull(deletedWish);
    }

    @Test
    void updateWish() {
            //Arrange
            Wish wishupdated = new Wish("TV", 1, "STORT TV", 2500, "www.tv.dk", 2);

            //ACT
            wishRepository.updateWish(wishupdated);

            //ASSERT
            assertEquals("TV", wishRepository.getSpecificWishById(1).getWishName());

    }
}