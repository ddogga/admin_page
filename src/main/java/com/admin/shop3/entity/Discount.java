package com.admin.shop3.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Discount {

    @Id
    @GeneratedValue
    @Column(name = "discount_id")
    private Long id;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    private int discount_amount;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
