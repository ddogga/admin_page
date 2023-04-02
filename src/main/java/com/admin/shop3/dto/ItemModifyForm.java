package com.admin.shop3.dto;

import com.admin.shop3.entity.state.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemModifyForm {

    private Long id;
    private String name;
    private int price;
    private int itemCost;
    private int stockQuantity;
    private ItemStatus itemStatus;
}
