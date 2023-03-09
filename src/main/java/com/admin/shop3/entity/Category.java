package com.admin.shop3.entity;


import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Category {


    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;



}

