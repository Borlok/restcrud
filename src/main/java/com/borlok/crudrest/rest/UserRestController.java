package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.UserDto;
import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.AccountService;
import com.borlok.crudrest.service.EventService;
import com.borlok.crudrest.service.FileService;
import com.borlok.crudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private UserService userService;
    private AccountService accountService;
    private FileService fileService;
    private EventService eventService;

    @Autowired
    public UserRestController(UserService userService,
                              AccountService accountService,
                              FileService fileService,
                              EventService eventService) {
        this.userService = userService;
        this.accountService = accountService;
        this.fileService = fileService;
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public UserDto getById (@PathVariable Integer id) {
        return UserDto.fromUser(userService.getById(id));
    }

    @GetMapping
    public List<UserDto> getAll () {
        return userService.findAll().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @PostMapping
    public UserDto create (@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        Account account = new Account();
        if (userDto.getAccount_id() != 0)
            account = accountService.getById(userDto.getAccount_id());
        account.setUser(user);
        user.setAccount(account);

        user.setFiles(userDto.getFile_ids().stream()
                .map(x -> fileService.getById(x))
                .peek(x-> x.setUser(user))
                .collect(Collectors.toList()));

        user.setEvents(userDto.getEvent_ids().stream()
                .map(x -> eventService.getById(x))
                .peek(x-> x.setUser(user))
                .collect(Collectors.toList()));

        return UserDto.fromUser(userService.create(user));
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }
}
