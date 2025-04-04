package org.example.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlist.model.User;
import org.example.wishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {
    private WishService wishService;

    public SessionController(WishService wishService) {
        this.wishService = wishService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/test")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("uid") String uid, @RequestParam("pw") String pw, HttpSession session, Model model) {
        if (wishService.login(uid, pw)) {
            session.setAttribute("user", new User(uid, pw));
            session.setMaxInactiveInterval(60);
            return "redirect:/wishlists";
        }
        model.addAttribute("wrongCredientials", true);
        return "login";
    }

    @GetMapping("admin")
    public String showAdmin(HttpSession session) {
        return isLoggedIn(session) ? "admin" : "login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
