package com.ROI.test.controller;

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
    public String searchUsersByUsername(@RequestParam(name = "username", required=false) String userName,
                                        @RequestParam(name = "email", required=false) String email,
                                        Model model) {
        if (userName != null) {
            model.addAttribute("users", userRepository.findByUserNameContaining(userName));
        }
        else {
            System.out.println("ELSE BRANCH OF PARAMETERS CHECK (userName != null)");
            if (email != null) {
                model.addAttribute("users", userRepository.findByEmail(email));
            }
            else{
                model.addAttribute("users", userRepository.findAll());
            }
        }
        return "ums";
    }
}
