package com.admin.shop3.entity;


import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private  String name;
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;



}

