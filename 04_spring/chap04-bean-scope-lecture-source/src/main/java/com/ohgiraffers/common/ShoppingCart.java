package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> items;
    // final을 붙이는 이유: 객체가 변경되서는 안됨, 초기화가 필수

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    /*설명. 카트에 물품 담는 기능*/
    public void addItem(Product item) {
        items.add(item);
    }

    /*설명. 카트에 물품 꺼내는 기능*/
    public List<Product> getItems() {
        return items;
    }


}
