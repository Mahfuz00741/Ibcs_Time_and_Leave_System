package com.ibcs.admin_test2.api;

import com.ibcs.admin_test2.dto.UserDto;
import com.ibcs.admin_test2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userApi")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    Page<UserDto> all() {

        return userService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/{id}")
    public UserDto findOne(@PathVariable Long id) {

        return userService.findById(id);
    }

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto newUser) {

        return userService.save(newUser);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto newUser, @PathVariable Long id) {

        return userService.update(newUser, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteById(id);
    }
}
