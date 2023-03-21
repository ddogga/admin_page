package com.admin.shop3.entity.state;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemStatus {

    EOS("END_OF_SALES","단종 상품"),
    OS("ON_SALES","판매 상품");

    private final String key;
    private final String title;
}
