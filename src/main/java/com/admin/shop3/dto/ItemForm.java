package com.admin.shop3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {

    private String name;
    private int price;
    private int itemCost;
    private int stockQuantity;

}
