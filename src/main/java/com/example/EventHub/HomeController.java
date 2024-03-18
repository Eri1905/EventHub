package com.example.EventHub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/profile")
    public String profile(){return "profile";}
    @RequestMapping("/login-email")
    public String loginEmail() {
        return "login-email";
    }

    @RequestMapping("/user/registration")
    public String reg() {
        return "registration";
    }
}
