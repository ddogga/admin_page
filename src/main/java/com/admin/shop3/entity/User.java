package com.admin.shop3.entity;

import com.admin.shop3.entity.state.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private  String name;
    private  String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
