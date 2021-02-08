package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.AccountDto;
import com.borlok.crudrest.model.Account;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.AccountService;
import com.borlok.crudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestController {
    private AccountService accountService;
    private UserService userService;

    @Autowired
    public AccountRestController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping
    public AccountDto create(@RequestBody AccountDto accountDto) {
        Account account = accountDto.toAccount();
        User user = new User();
        if (accountDto.getUser_id() != 0)
            user = userService.getById(accountDto.getUser_id());
        user.setAccount(account);
        account.setUser(user);
        return AccountDto.fromAccount(accountService.create(account));
    }

    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable("id") Integer id) {
        return AccountDto.fromAccount(accountService.getById(id));

    }

    @GetMapping
    public List<AccountDto> getAll () {
        return accountService.getAll().stream().map(AccountDto::fromAccount).collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        accountService.deleteById(id);
    }
}
