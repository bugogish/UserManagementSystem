package com.ROI.test.controller;

import com.ROI.test.model.User;
import com.ROI.test.model.UserRepository;
import com.ROI.test.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @RequestMapping(value = "/new_user", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "new_user";
    }

    @RequestMapping(value = "/new_user/", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userForm") User userForm,
                                           BindingResult bindingResult,
                                           Model model) {
//            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "new_user";
            }

            userRepository.save(userForm);

            securityService.autologin(userForm.getUserName(), userForm.getPassword());

            return "redirect:/ums";
    }
}