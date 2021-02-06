package com.borlok.crudrest.service;

import com.borlok.crudrest.model.User;
import com.borlok.crudrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create (User user) {
        userRepository.save(user);
        userRepository.flush();
        return user;
    }

    public User getById(int id) {
        User user = userRepository.getOne(id);
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {

    }
}
