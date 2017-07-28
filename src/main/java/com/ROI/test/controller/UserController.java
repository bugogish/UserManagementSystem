package com.ROI.test.controller;

import com.ROI.test.model.User;
import com.ROI.test.model.UserRepository;
import com.ROI.test.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
         User user = userRepository.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/new_user")
    public String serveNewUserPage(Model model) {
        model.addAttribute("user", new User());

        return "new_user";
    }

    @PostMapping("/new_user")
    public String createUser(@ModelAttribute User user,
                             BindingResult bindingResult,
                             Model model) {
        System.out.println(user.getUserName());

//        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "new_user";
        }

        userRepository.save(user);

        return "redirect:/ums";
    }
}