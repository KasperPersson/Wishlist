package org.example.wishlist.controller;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.service.WishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WishService wishService;

    @InjectMocks
    private WishController wishController;

    @BeforeEach
    void setup(){
    mockMvc = MockMvcBuilders.standaloneSetup(wishController).build();
    }

    @Test
    void index() {

    }

    @Test
    void getAllWishLists() {
        //arrange
       Wishlist wishlist1 = new Wishlist(1, new ArrayList<>(), 200, "julegave ønsker", "Jul 2025");
       Wishlist wishlist2 = new Wishlist(2, new ArrayList<>(), 700, "julegave ønsker sidste år", "Jul 2024");
       List<Wishlist> mockLists = List.of(wishlist1, wishlist2);

       when(wishService.getAllWishLists()).thenReturn(mockLists);

       //act & assert

    }

    @Test
    void viewWishlist() {
    }

    @Test
    void viewWish() {
    }

    @Test
    void editWish() {
    }

    @Test
    void updateWish() {
    }

    @Test
    void deleteWishList() {
    }
}