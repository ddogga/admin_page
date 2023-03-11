package com.admin.shop3.controller;

import com.admin.shop3.entity.Item;
import com.admin.shop3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping("/item/income_ranking")
    private ResponseEntity getItemRanking() {
        return ResponseEntity.of(Optional.ofNullable(itemService.getSalesTop5Items()));
    }
}
