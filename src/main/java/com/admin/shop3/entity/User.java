package com.admin.shop3.entity;

import com.admin.shop3.dto.UserResDto;
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
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserResDto toResDto() {
        UserResDto dto = UserResDto.builder()
                .id(this.id)
                .name(this.name)
                .createDate(this.getCreatedDate())
                .role(this.role)
                .build();
        return dto;
    }
}
