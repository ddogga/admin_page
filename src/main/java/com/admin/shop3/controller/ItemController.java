package com.admin.shop3.controller;

import com.admin.shop3.entity.Item;
import com.admin.shop3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/items/new")
    public ResponseEntity create(@RequestBody ItemForm form) {

        Item item = Item.builder()
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .salesQuantity(0)
                .build();

        return ResponseEntity.ok(itemService.saveItem(item));
    }

//    @GetMapping("/item/incom_ranking")
//    private List<Item> getItemRanking() {
//
//    }
}
