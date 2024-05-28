package com.company.firstApplication.firstApplicaton.controller;

import com.company.firstApplication.firstApplicaton.model.User;
import com.company.firstApplication.firstApplicaton.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUserList() {
        List<User> users = userService.getAllUsers();
        logger.info("UserController::getUserList get all users={}",users);
        return users;
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        logger.info("UserController::getUserById get User by Id = {}", user);
        return user;
    }

    @PostMapping(value = "/user")
    public User addUser(@RequestBody User user) {
        User userSaved = userService.addUser(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getUserType()));
        return userSaved;
    }

}