package com.example.EventHub.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration/add")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/registration/submit")
    public String postRegister(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }else{

            userRepository.save(user);
            return "/login";
        }
    }
}
