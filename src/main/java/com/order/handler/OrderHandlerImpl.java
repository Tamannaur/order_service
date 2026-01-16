package com.order.handler;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.entity.Order;
import com.order.repository.OrderRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

public class OrderHandlerImpl implements OrderHandler {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderHandlerImpl(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderResponse handleOrder(OrderRequest request) {

        String inventoryUrl = "http://localhost:8080/inventory/update";

        HttpHeaders headers = new HttpHeaders();
        headers.set("fromOutSide", String.valueOf(true));
        HttpEntity<OrderRequest> entity = new HttpEntity<>(request, headers);

        restTemplate.postForEntity(inventoryUrl, entity, String.class);


        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());
        order.setProductName("Product-" + request.getProductId());
        repository.save(order);


        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setProductId(order.getProductId());
        response.setProductName(order.getProductName());
        response.setQuantity(order.getQuantity());
        response.setStatus(order.getStatus());
        response.setReservedFromBatchIds(List.of());
        response.setMessage("Order placed. Inventory reserved.");

        return response;
    }
}

