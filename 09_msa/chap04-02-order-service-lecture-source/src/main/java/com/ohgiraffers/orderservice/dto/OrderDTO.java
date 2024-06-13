package com.ohgiraffers.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private int orderCode;
    private int userId;
    private String orderDate;
    private String orderTime;
    private int totalOrderPrice;

    private List<OrderMenuDTO> orderMenus;
}
