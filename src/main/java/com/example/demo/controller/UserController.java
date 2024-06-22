package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RabbitMQProducerService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitMQProducerService rabbitMQProducerService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        rabbitMQProducerService.sendMessage(user);
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
