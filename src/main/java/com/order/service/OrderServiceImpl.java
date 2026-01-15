package com.order.service;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.handler.OrderHandler;
import com.order.handler.OrderHandlerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderHandlerFactory factory;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        OrderHandler handler = factory.getHandler("DEFAULT");
        return handler.handleOrder(request);
    }
}
