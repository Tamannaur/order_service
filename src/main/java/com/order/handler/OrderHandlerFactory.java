package com.order.handler;

import com.order.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderHandlerFactory {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderHandlerFactory(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public OrderHandler getHandler(String type) {
        if (type.equals("DEFAULT")) {
            return new OrderHandlerImpl(repository, restTemplate);
        }
        throw new IllegalArgumentException("Unknown handler type: " + type);
    }
}

