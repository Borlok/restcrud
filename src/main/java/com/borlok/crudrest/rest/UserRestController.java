package com.borlok.crudrest.rest;

import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getById (@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAll () {
        return userService.findAll();
    }

    @PostMapping
    public User create (User user) {
        return user;
    }

    @PostMapping("/{id}")
    public void deleteById (@PathVariable Integer id) {
        userService.deleteById(id);
    }


}
