package com.example.EventHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
    @GetMapping("/profile")
    public String profileForm(){
        return "profile";
    }

}
