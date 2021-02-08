package com.borlok.crudrest.service;

import com.borlok.crudrest.model.User;
import com.borlok.crudrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create (User user) {
        User returnedUser = userRepository.save(user);
        System.out.println("Возвращаем при сохранении " + returnedUser);
        userRepository.flush();
        return returnedUser;
    }

    public User getById(int id) {
        return userRepository.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
