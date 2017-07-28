package com.ROI.test.controller;

import com.ROI.test.model.User;
import com.ROI.test.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {
    private final UserRepository userRepository;

    @Autowired
    MainPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/ums", "/"})
    public String searchUsersByUsername(@RequestParam(name = "username", required=false) String userName, Model model) {
        if (userName != null) {
            User user = userRepository.findByUserName(userName).orElse(null);
            model.addAttribute("users", user);
        }
        else {
            model.addAttribute("users", userRepository.findAll());
        }
        return "ums";
    }
}
