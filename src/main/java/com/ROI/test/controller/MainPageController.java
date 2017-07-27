package com.ROI.test.controller;

import com.ROI.test.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainPageController {
    private final UserRepository userRepository;

    @Autowired
    MainPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/ums")
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "ums";
    }

}
