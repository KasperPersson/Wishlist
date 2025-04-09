package org.example.wishlist.controller;

import org.example.wishlist.model.Wish;
import org.example.wishlist.model.Wishlist;
import org.example.wishlist.service.WishService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(WishController.class)
class WishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishService wishService;

    @BeforeEach
    void setup() {
        // initialiserer wishService og wishController, så mocks er klar til brug før test kører
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void index() {
    }

    @Test
    void getAllWishLists() throws Exception {
        //arrange
        Wishlist wishlist1 = new Wishlist(1, new ArrayList<>(), "julegave ønsker", "Jul 2025");
        Wishlist wishlist2 = new Wishlist(2, new ArrayList<>(), "julegave ønsker sidste år", "Jul 2024");
        List<Wishlist> mockLists = List.of(wishlist1, wishlist2);

        when(wishService.getAllWishlists()).thenReturn(mockLists);

        //act & assert
        mockMvc.perform(get("/wishlists")) //laver get request til controller
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist"))
                .andExpect(model().attribute("wishLists", mockLists));

        verify(wishService, times(1)).getAllWishlists(); //sikre metoden kaldes én gang, ikke nul eller flere gange
    }

    @Test
    void viewWishlist() throws Exception {
        //arrange
        int wishlistID = 1;
        Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), "fødselsdagsønsker", "30 års fødselsdag");
        List<Wish> wishes = List.of(new Wish("landsholdstrøje", 1, "det danske herrelandshold (fodbold) nye trøje", 499, "www.dbu.dk", 1),
                new Wish("Sims 4", 2, "til playstation", 249, "www.ea.com", 1));

        //mocker wishService så controller får de dataer vi har indtastet
        when(wishService.getWishlistById(wishlistID)).thenReturn(wishlist);
        when(wishService.getAllWishesById(wishlistID)).thenReturn(wishes);

        //act & assert
        mockMvc.perform(get("/wishlists/{id}", wishlistID))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist-details"))
                .andExpect(model().attribute("wishlist", wishlist))
                .andExpect(model().attribute("wishes", wishes));

        verify(wishService, times(1)).getWishlistById(wishlistID);
        verify(wishService, times(1)).getAllWishesById(wishlistID);
    }

    @Test
    void viewWish() throws Exception {
        //arrange
        int wishID = 1;
        Wish wish = new Wish("træ abe", wishID, "Den største fra Kaj Boyesen", 1299, "www.bahne.dk", 1);
        when(wishService.getSpecificWishById(wishID)).thenReturn(wish);

        //act & assert
        mockMvc.perform(get("/wishes/{id}", wishID))
                .andExpect(status().isOk())
                .andExpect(view().name("wish-details"))
                .andExpect(model().attribute("wish", wish));

        verify(wishService, times(1)).getSpecificWishById(wishID);

    }

    @Test
    void editWish() throws Exception {
        //arrange
        int wishID = 1;
        Wish wish = new Wish("træ abe", wishID, "Den største fra Kaj Boyesen", 1299, "www.bahne.dk", 1);
        when(wishService.getSpecificWishById(wishID)).thenReturn(wish);

        //act & assert
        mockMvc.perform(get("/wishes/{id}/edit", wishID))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-wish"))
                .andExpect(model().attribute("wish", wish));

        verify(wishService, times(1)).getSpecificWishById(wishID);
    }

    @Test
    void updateWish() throws Exception {
        // Arrange
        Wish wish = new Wish("Playstation 5", 1, "Konsol med 1 controller", 4999, "www.ps5.dk", 2);

        // Act & Assert
        mockMvc.perform(post("/update")
                        .param("wishName", wish.getWishName())
                        .param("wishID", String.valueOf(wish.getWishID()))
                        .param("description", wish.getDescription())
                        .param("price", String.valueOf(wish.getPrice()))
                        .param("link", wish.getLink())
                        .param("wishlistId", String.valueOf(wish.getWishlistId()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishes/" + wish.getWishID()));

        verify(wishService, times(1)).updateWish(any(Wish.class));
    }


    @Test
    void showAddWish() throws Exception {
        //arrange
        int wishlistID = 1;
        Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), "julegaver", "juleønsker 2026");
        when(wishService.getWishlistById(wishlistID)).thenReturn(wishlist);

        //act & assert
        mockMvc.perform(get("/wishlists/{id}/add-wish", wishlistID))
                .andExpect(status().isOk())
                .andExpect(view().name("add-wish"))
                .andExpect(model().attribute("wishlist", wishlist))
                .andExpect(model().attributeExists("wish"));

        verify(wishService, times(1)).getWishlistById(wishlistID);
    }

    @Test
    void addingWish() throws Exception {
        //Arrange
        int wishlistID = 1;
        Wish wish = new Wish("Playstation 5", 1, "Konsol med 1 controller", 4999, "www.ps5.dk", wishlistID);

        // Act & Assert
        mockMvc.perform(post("/wishlists/{id}/adding", wishlistID)
                        .param("wishName", wish.getWishName())
                        .param("wishID", String.valueOf(wish.getWishID()))
                        .param("description", wish.getDescription())
                        .param("price", String.valueOf(wish.getPrice()))
                        .param("link", wish.getLink())
                        .param("wishlistId", String.valueOf(wish.getWishlistId()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlists/" + wishlistID));

        verify(wishService, times(1)).addWish(any(Wish.class));
    }


    @Test
    void deleteWishList() throws Exception {
        //arrange
        int wishlistID = 1;
        Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), "julegaver", "juleønsker 2026");
        when(wishService.getWishlistById(wishlistID)).thenReturn(wishlist);

        //act & assert
        mockMvc.perform(post("/wishlists/delete/{id}", wishlistID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlists"));

        verify(wishService, times(1)).getWishlistById(wishlistID);
        verify(wishService, times(1)).deleteWishlistById(wishlist);
    }


    @Test
    void deleteWish() throws Exception {
        //arrange
        int wishID = 1;
        Wish wish = new Wish("Playstation 5", wishID, "Konsol med 1 controller", 4999, "www.ps5.dk", 1);
        when(wishService.getSpecificWishById(wishID)).thenReturn(wish);

        //act & assert
        mockMvc.perform(post("/wishes/delete/{id}", wishID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlists/" + wishID));

        verify(wishService, times(1)).getSpecificWishById(wishID);
        verify(wishService, times(1)).deleteWishById(wishID);

    }

    @Test
    void createWishlistForm() throws Exception {
        mockMvc.perform(get("/wishlists/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlists-create"))
                .andExpect(model().attributeExists("wishlist"));
    }

    @Test
    void createWishlist() throws Exception {
        //arrange
        int wishlistID = 1;
        Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), "julegaver", "juleønsker 2026");
        when(wishService.addWishlist(any(Wishlist.class))).thenReturn(wishlistID);

        //act & assert
        mockMvc.perform(post("/wishlists/create")
                        .param("wishlistID", "0")
                        .param("wishlistDesc", wishlist.getWishlistDesc())
                        .param("wishlistName", wishlist.getWishlistName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlists/" + wishlistID));

        verify(wishService, times(1)).addWishlist(any(Wishlist.class));
    }

    @Test
    void editWishlistform() throws Exception{
        //arrange
        int wishlistID = 1;
        Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), "julegaver", "juleønsker 2026");
        when(wishService.getWishlistById(wishlistID)).thenReturn(wishlist);

        //act & assert
        mockMvc.perform(get("/wishlists/{id}/edit",wishlistID))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-wishlist"))
                .andExpect(model().attribute("wishlist", wishlist));

        verify(wishService,times(1)).getWishlistById(wishlistID);

    }

        @Test
        void updateWishlist() throws Exception {
            //arrange
            int wishlistID = 1;
            Wishlist wishlist = new Wishlist(wishlistID, new ArrayList<>(), "julegaver", "juleønsker 2026");

            //act & assert
            mockMvc.perform(post("/wishlists/edit")
                            .param("wishlistID", String.valueOf(wishlist.getWishlistID()))
                            .param("wishlistDesc", wishlist.getWishlistDesc())
                            .param("wishlistName", wishlist.getWishlistName()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/wishlists/" + wishlistID));

            verify(wishService,times(1)).updateWishlist(any(Wishlist.class));
        }

}


