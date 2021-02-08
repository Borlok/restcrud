package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.FileDto;
import com.borlok.crudrest.model.File;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.FileService;
import com.borlok.crudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
public class FileRestService {
    private FileService fileService;
    private UserService userService;

    @Autowired
    public FileRestService(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public FileDto create(@RequestBody FileDto fileDto) {
        File file = fileDto.toFile();
        User user = new User();
        if (fileDto.getUser_id() != 0)
            user = userService.getById(fileDto.getUser_id());
        user.getFiles().add(file);
        file.setUser(user);
        return FileDto.fromFile(fileService.create(file));
    }

    @GetMapping("/{id}")
    public FileDto getById (@PathVariable("id") Integer id) {
        return FileDto.fromFile(fileService.getById(id));
    }

    @GetMapping
    public List<FileDto> getAll() {
        return fileService.getAll().stream().map(FileDto::fromFile).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable("id") Integer id) {
        fileService.deleteById(id);
    }
}
