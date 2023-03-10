package com.admin.shop3.entity;


import com.admin.shop3.repository.NotEnoughStockException;
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

    @Column(name = "sales_quantity")
    private int salesQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}

