package com.admin.shop3.service;


import com.admin.shop3.entity.Item;
import com.admin.shop3.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
