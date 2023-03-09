package com.admin.shop3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class UserHistory {

    @Id
    @GeneratedValue
    @Column(name = "quit_user_id")
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "quit_date")
    private LocalDateTime quitDate;

}

