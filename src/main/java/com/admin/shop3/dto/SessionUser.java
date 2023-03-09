package com.admin.shop3.dto;




import com.admin.shop3.entity.User;
import com.admin.shop3.entity.state.Role;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private Role role;


    public SessionUser(User user){
        this.name = user.getName();
        this.role = user.getRole();
    }
}

