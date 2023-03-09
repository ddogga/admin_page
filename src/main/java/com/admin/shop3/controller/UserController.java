package com.admin.shop3.controller;



import com.admin.shop3.dto.LoginForm;
import com.admin.shop3.dto.UserForm;
import com.admin.shop3.entity.User;
import com.admin.shop3.entity.state.Role;
import com.admin.shop3.repository.UserRepository;
import com.admin.shop3.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/users/admin/new")
    public ResponseEntity create(@RequestBody @Valid UserForm form, BindingResult result) {

        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        User user = userService.join(form, Role.ADMIN);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/users/admin/login")
    public String login(@RequestBody LoginForm form) {

        return userService.login(form);
    }


    @GetMapping("/users/role_users")
    public ResponseEntity getUsers(String role){
        if (role.equals("user")) {
            return ResponseEntity.ok(userRepository.findAllByRole(Role.USER));
        }
        return ResponseEntity.ok(userRepository.findAllByRole(Role.ADMIN));
    }


}

