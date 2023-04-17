package com.admin.shop3.dto;


import com.admin.shop3.entity.state.ItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDto {

    private Long id;
    private String name;
    private int price;
    private ItemStatus itemStatus;
    private int itemCost;
    private int stockQuantity;

}
