package com.swagger.coderestapi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.swagger.coderestapi.entity.Order;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class OrderIT {

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void getOrders() {

        ResponseEntity<Order[]> response = restTemplate.getForEntity(
                "/rest/consultapedidos",
                Order[].class
        );

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());

        Order[] orders = response.getBody();

        assertNotNull(orders);
        //assertEquals(1, orders.length);

	}
}
