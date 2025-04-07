package org.example.wishlist.controller;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.service.WishService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


import java.util.ArrayList;
import java.util.List;

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

    @AfterEach
    void tearDown(){}

    @Test
    void index() {}

    @Test
    void getAllWishLists() throws Exception {
        //arrange
       Wishlist wishlist1 = new Wishlist(1, new ArrayList<>(), 200, "julegave ønsker", "Jul 2025");
       Wishlist wishlist2 = new Wishlist(2, new ArrayList<>(), 700, "julegave ønsker sidste år", "Jul 2024");
       List<Wishlist> mockLists = List.of(wishlist1, wishlist2);

       when(wishService.getAllWishLists()).thenReturn(mockLists);

       //act & assert
        mockMvc.perform(get("/wishlists")) //laver get request til controller
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist"))
                .andExpect(model().attribute("wishlists",mockLists));

        verify(wishService,times(1)).getAllWishLists(); //sikre metoden kaldes én gang, ikke nul eller flere gange
    }

    @Test
    void viewWishlist() throws Exception {
        //arrange
        int wishlistID = 1;
        Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), 1200, "fødselsdagsønsker", "30 års fødselsdag");
        List<Wish> wishes = List.of(new Wish("landsholdstrøje", 1, "det danske herrelandshold (fodbold) nye trøje", 499, "www.dbu.dk"),
                new Wish("Sims 4", 2, "til playstation", 249, "www.ea.com"));

        //mocker wishService så controller får de dataer vi har indtastet
        when(wishService.getWishlistById(wishlistID)).thenReturn(wishlist);
        when(wishService.getAllWishesById(wishlistID)).thenReturn(wishes);

        //act & assert
        mockMvc.perform(get("/wishlists/{id}",wishlistID))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist-details"))
                .andExpect(model().attribute("wishlist", wishlist))
                .andExpect(model().attribute("wishes", wishes));

        verify(wishService,times(1)).getWishlistById(wishlistID);
        verify(wishService,times(1)).getAllWishesById(wishlistID);
    }

    @Test
    void viewWish() throws Exception{
        //arrange
        int wishID = 1;
        Wish wish = new Wish("træ abe", wishID, "Den største fra Kaj Boyesen", 1299, "www.bahne.dk");
        when(wishService.getSpecificWishById(wishID)).thenReturn(wish);

        //act & assert
        mockMvc.perform(get("/wishes/{id}", wishID))
                .andExpect(status().isOk())
                .andExpect(view().name("wish-details"))
                .andExpect(model().attribute("wish", wish));

        verify(wishService,times(1)).getSpecificWishById(wishID);

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