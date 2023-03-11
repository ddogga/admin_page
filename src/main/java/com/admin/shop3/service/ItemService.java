package com.admin.shop3.service;


import com.admin.shop3.entity.Item;
import com.admin.shop3.entity.User;
import com.admin.shop3.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    @Transactional
    public Item saveItem(Item item) {
        Item newItem = itemRepository.save(item);
        return newItem;
    }

    public List<Item> getSalesTop5Items () {

        List<Item> items = itemRepository.findAllByOrderBySalesQuantityDesc()
                .stream()
                .limit(5)
                .collect(Collectors.toList());
        return items;
    }

    @Transactional
    public int delete(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            itemRepository.delete(item.get());
            return 1;
        }
        return 0;
    }
}
