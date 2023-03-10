package com.admin.shop3.controller;


import com.admin.shop3.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("get_session")
    public String getSession(HttpSession session){
        SessionUser user = (SessionUser) session.getAttribute("user");
        return user.getName();
    }
}
