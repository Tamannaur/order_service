package com.order.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrderShouldReturnResponseEntity() {

        OrderRequest request = new OrderRequest();
        request.setProductId(1003L);
        request.setQuantity(2);

        OrderResponse response = new OrderResponse();
        response.setOrderId(1L);
        response.setStatus("SUCCESS");

        when(orderService.placeOrder(request)).thenReturn(response);

        ResponseEntity<OrderResponse> result = orderController.placeOrder(request);

        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getOrderId()).isEqualTo(1L);
        assertThat(result.getBody().getStatus()).isEqualTo("SUCCESS");

        verify(orderService, times(1)).placeOrder(request);
    }
}
