package com.ROI.test.controller;

import com.ROI.test.model.User;
import com.ROI.test.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

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

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

//    private void validateUser(Long userId) {
//        User user = userRepository.findOne(userId);
//        (() -> new UserNotFoundException(userId));
//    }
}