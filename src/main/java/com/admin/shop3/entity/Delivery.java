package com.admin.shop3.entity;

import com.admin.shop3.entity.state.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;
@Getter
@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

//    @OneToOne(mappedBy = "delivery",fetch = LAZY)
//    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
