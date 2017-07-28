package com.ROI.test.controller;

import com.ROI.test.model.User;
import com.ROI.test.model.UserRepository;
import com.ROI.test.model.UserValidator;
import com.ROI.test.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/user/")
    ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}",
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

        userValidator.validate(user, bindingResult);
        System.out.println(user.getAddress().getZip());

        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT HAS ERRORS");
//            StringBuilder error_message = new StringBuilder("Errors list: ");
//
//            for (Object object : bindingResult.getAllErrors()) {
//                boolean isAlreadyPrinted = false;
//                if(object instanceof FieldError) {
//                    FieldError fieldError = (FieldError) object;
////                    error_message.append(fieldError.getCode()).append(" ");
//                    error_message.append(messageSource.getMessage(fieldError, null));
//                    isAlreadyPrinted = true;
//                }
//                if(object instanceof ObjectError && !isAlreadyPrinted) {
//                    ObjectError objectError = (ObjectError) object;
//                    error_message.append(messageSource.getMessage(objectError, null));
//                }
//            }
//
//            model.addAttribute("error_message", error_message.toString());
            return "new_user";
        }

        userRepository.save(user);

        return "redirect:/ums";
    }
}