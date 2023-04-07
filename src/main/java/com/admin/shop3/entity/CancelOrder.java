package com.admin.shop3.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class CancelOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "cancel_order_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    private Order order;

    private String reason;
}
