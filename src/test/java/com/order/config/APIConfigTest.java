package com.order.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.order.config.APIConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = APIConfig.class)
class APIConfigTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void restTemplateBeanShouldBeLoaded() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    void restTemplateShouldWorkForSimpleCall() {
        assertThat(restTemplate.getRequestFactory()).isNotNull();
    }
}
