package com.order.handler;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.entity.Order;
import com.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderHandlerImplTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderHandlerImpl handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleOrderShouldSaveOrderAndReturnResponse() {

        OrderRequest request = new OrderRequest();
        request.setProductId(1002L);
        request.setQuantity(2);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setOrderId(1L);
            return order;
        });

        when(restTemplate.postForEntity(
                eq("http://localhost:8080/inventory/update"),
                any(HttpEntity.class),
                eq(String.class))
        ).thenReturn(null);

        OrderResponse response = handler.handleOrder(request);

        assertThat(response.getOrderId()).isEqualTo(1L);
        assertThat(response.getProductId()).isEqualTo(1002L);
        assertThat(response.getStatus()).isEqualTo("PLACED");
        assertThat(response.getMessage()).isEqualTo("Order placed. Inventory reserved.");

        verify(repository, times(1)).save(any(Order.class));
        verify(restTemplate, times(1))
                .postForEntity(eq("http://localhost:8080/inventory/update"), any(), eq(String.class));
    }
}
