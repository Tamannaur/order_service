package com.order.handler;


import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;

public interface OrderHandler {
    OrderResponse handleOrder(OrderRequest request);
}

