package com.admin.shop3.controller;

import com.admin.shop3.dto.ItemForm;
import com.admin.shop3.entity.Item;
import com.admin.shop3.repository.ItemRepository;
import com.admin.shop3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @PostMapping("/item/new")
    public ResponseEntity create(@RequestBody ItemForm form) {

        Item item = Item.builder()
                .name(form.getName())
                .price(form.getPrice())
                .itemCost(form.getItemCost())
                .stockQuantity(form.getStockQuantity())
                .salesQuantity(0)
                .build();

        return ResponseEntity.ok(itemService.saveItem(item));
    }

    @GetMapping("/item/income_ranking")
    private ResponseEntity getItemRanking() {
        return ResponseEntity.of(Optional.ofNullable(itemService.getSalesTop5Items()));
    }

    @GetMapping("/items")
    private ResponseEntity getAllItem() {
        return ResponseEntity.of(Optional.ofNullable(itemRepository.findAll()));
    }

    @DeleteMapping("/item")
    public Map<String, Object> deleteItem(Long id) {
        Map<String, Object> response = new HashMap<>();

        if(itemService.delete(id) > 0) {
            response.put("result", "SUCCESS");
        }else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 상품이 없습니다.");
        }

        return response;
    }
}
