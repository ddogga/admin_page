package com.admin.shop3.controller;



import com.admin.shop3.dto.LoginForm;
import com.admin.shop3.dto.UserForm;
import com.admin.shop3.entity.User;
import com.admin.shop3.entity.state.Role;
import com.admin.shop3.repository.UserRepository;
import com.admin.shop3.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity create(@RequestBody @Valid UserForm form, BindingResult result) {

        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        User user = userService.join(form);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/user/idcheck")
    public int idCheck(String id) {
        User user = userRepository.findOneByName(id);
        if (user == null) {return 0;}
        return 1;
    }


    @PostMapping("/user/admin/login")
    public int login(@RequestBody LoginForm form) {

        return userService.login(form);
    }

    @PostMapping("/user/logout")
    public void logout(HttpSession session) {
        log.info("로그아웃");
        session.invalidate();
    }


    @GetMapping("/users")
    public ResponseEntity getUsers(@RequestParam int page, int size){
        log.info("page : " + page + " size : " + size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUsers(pageRequest));

    }

    @DeleteMapping("/user")
    public Map<String, Object> deleteUser(Long id) {
        Map<String, Object> response = new HashMap<>();

        if(userService.delete(id) > 0) {
            response.put("result", "SUCCESS");
        }else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }
        return response;
    }


}

