package com.admin.shop3.service;


import com.admin.shop3.dto.LoginForm;
import com.admin.shop3.dto.SessionUser;
import com.admin.shop3.dto.UserForm;
import com.admin.shop3.entity.User;
import com.admin.shop3.entity.state.Role;
import com.admin.shop3.exception.UserDuplicationException;
import com.admin.shop3.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    /**
     * 회원 가입
     */
    @Transactional
    public User join(UserForm form, Role role){
        validateDuplicateMember(form.getName());
        User user = buildUser(form, role);
        return userRepository.save(user);
    }

    private User buildUser(UserForm form, Role role){
        User user = User.builder()
                .name(form.getName())
                .password(form.getPassword())
                .role(role)
                .build();
        return user;
    }


    private void validateDuplicateMember(String userName) {
        //실무에서는 멀티 쓰레드를 고려하여 name을 유니크 제약 조건을 걸어 둬야함.
        User findUser = userRepository.findOneByName(userName);
        if(findUser != null){
            throw new UserDuplicationException();
        }
    }

    public String login(LoginForm form) {
        User findUser = userRepository.findOneByName(form.getName());
        if (findUser == null) {
            return "아이디가 일치하지 않습니다.";
        }

        if(!form.getPassword().equals(findUser.getPassword())){
            return "비밀번호가 일치하지 않습니다.";
        }

        httpSession.setAttribute("user", new SessionUser(findUser));
        return "로그인 성공";
    }



}
