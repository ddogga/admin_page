package com.admin.shop3.service;


import com.admin.shop3.dto.*;
import com.admin.shop3.entity.User;
import com.admin.shop3.entity.state.Role;
import com.admin.shop3.exception.UserDuplicationException;
import com.admin.shop3.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
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
    public User join(UserForm form){
        validateDuplicateMember(form.getId());
        User user = buildUser(form);
        return userRepository.save(user);
    }

    private User buildUser(UserForm form){
        User user = User.builder()
                .name(form.getId())
                .password(form.getPassword())
                .role(form.getRole())
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

    public int login(LoginForm form) {
        User findUser = userRepository.findOneByName(form.getId());
        if (findUser == null) {
            return -1;
        }

        if(!form.getPassword().equals(findUser.getPassword())){
            return 0;
        }

        httpSession.setAttribute("user", new SessionUser(findUser));
        return 1;
    }

    @Transactional
    public int delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
            return 1;
        }
        return 0;
    }



    public Page<UserResDto> getUsers(Pageable pageable) {

        return userRepository.findAllByRoleOrderByIdDesc(Role.USER,pageable)
                .map(User::toResDto);

    }





}

