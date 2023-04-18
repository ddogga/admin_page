package com.admin.shop3.service;


import com.admin.shop3.dto.ItemDto;
import com.admin.shop3.dto.ItemModifyForm;
import com.admin.shop3.entity.Item;
import com.admin.shop3.entity.User;
import com.admin.shop3.exception.ItemNotFoundException;
import com.admin.shop3.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
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

    public List<Item> getSalesTop10Items () {

        List<Item> items = itemRepository.findTop10ByOrderBySalesQuantityDesc();

        return items;
    }

    public Page<ItemDto> getItems(Pageable pageable) {

        return itemRepository.findAllByOrderByIdDesc(pageable)
                .map(Item::toDto);
    }

//    @Transactional
//    public int delete(Long id) {
//        Optional<Item> item = itemRepository.findById(id);
//        if(item.isPresent()) {
//            itemRepository.delete(item.get());
//            return 1;
//        }
//        return 0;
//    }

    @Transactional
    public String updateItem(ItemModifyForm form) {
        Item findItem = itemRepository.findItemById(form.getId());
        if(findItem == null) {
            throw new ItemNotFoundException();
        }
        findItem.update(form);
        return "상품 정보 변경";
    }
}
