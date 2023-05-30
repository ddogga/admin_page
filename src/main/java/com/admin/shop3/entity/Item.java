package com.admin.shop3.entity;


import com.admin.shop3.dto.ItemDto;
import com.admin.shop3.dto.ItemModifyForm;
import com.admin.shop3.entity.state.ItemStatus;
import com.admin.shop3.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private  String name;
    private int price;

    /**
     * 상품 단종 여부
     */
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    /**
     * 상품 원가
     */
    @Column(name = "item_cost")
    private int itemCost;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "sales_quantity")
    private int salesQuantity;



//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;


    @Builder
    public Item(String name, int price, int stockQuantity, int salesQuantity, int itemCost, ItemStatus itemStatus) {
        this.name = name;
        this.price = price;
        this.itemCost = itemCost;
        this.stockQuantity = stockQuantity;
        this.salesQuantity = salesQuantity;
        this.itemStatus = itemStatus;
    }

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

    /**
     * sales_quantity 증가
     *
     */

    public void increaseSales(int quantity){
        this.salesQuantity += quantity;
    }


    /**
     * update Item
     */
    public void update(ItemModifyForm form) {
        this.name = form.getName();
        this.price = form.getPrice();
        this.itemCost = form.getItemCost();
        this.stockQuantity = form.getStockQuantity();
        this.itemStatus =form.getItemStatus();
    }


    public ItemDto toDto() {
        return ItemDto.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .itemCost(this.itemCost)
                .itemStatus(this.itemStatus)
                .stockQuantity(this.stockQuantity)
                .build();
    }

}

