package com.order.service;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.handler.OrderHandler;
import com.order.handler.OrderHandlerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    private OrderHandlerFactory factory;

    @Mock
    private OrderHandler handler;

    @InjectMocks
    private OrderServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrderShouldDelegateToHandler() {
        // Arrange
        OrderRequest request = new OrderRequest();
        request.setProductId(1002L);
        request.setQuantity(2);

        OrderResponse response = new OrderResponse();
        response.setOrderId(1L);
        response.setStatus("PLACED");

        when(factory.getHandler("DEFAULT")).thenReturn(handler);

        when(handler.handleOrder(any(OrderRequest.class))).thenReturn(response);

        OrderResponse result = service.placeOrder(request);

        assertThat(result.getOrderId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo("PLACED");

        verify(factory, times(1)).getHandler("DEFAULT");
        verify(handler, times(1)).handleOrder(request);
    }
}
