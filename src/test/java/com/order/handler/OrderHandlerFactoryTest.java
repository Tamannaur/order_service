package com.order.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class OrderHandlerFactoryTest {

    private final OrderRepository repository = mock(OrderRepository.class);
    private final RestTemplate restTemplate = mock(RestTemplate.class);

    private final OrderHandlerFactory factory = new OrderHandlerFactory(repository, restTemplate);

    @Test
    void getHandlerShouldReturnOrderHandlerImplForDefaultType() {
        OrderHandler handler = factory.getHandler("DEFAULT");
        assertThat(handler).isInstanceOf(OrderHandlerImpl.class);
    }

    @Test
    void getHandlerShouldThrowExceptionForUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> factory.getHandler("UNKNOWN"));
    }
}
