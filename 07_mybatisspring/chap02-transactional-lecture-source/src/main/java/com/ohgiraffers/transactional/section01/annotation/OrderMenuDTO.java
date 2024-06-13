package com.ohgiraffers.transactional.section01.annotation;

public class OrderMenuDTO {
    private int menuCode;
    private int orderAmount;

    public OrderMenuDTO() {
    }

    public OrderMenuDTO(int menuCode, int orderAmount) {
        this.menuCode = menuCode;
        this.orderAmount = orderAmount;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }
}
