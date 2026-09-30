package com.swagger.coderestapi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.swagger.coderestapi.entity.Item;
import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.enums.StatusEnum;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class OrderIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getOrders() {
	    
		// Arrange
		Item item1 = new Item("Laranja", 12, new BigDecimal("1.00"));
		Item item2 = new Item("Banana", 12, new BigDecimal("1.00"));
		Item item3 = new Item("Maca", 12, new BigDecimal("1.00"));

		List<Item> itemList1 = Arrays.asList(item1, item2, item3);

		Order order1 = new Order(1, 10, itemList1, new BigDecimal("7.50"), StatusEnum.APPROVED, LocalDate.now(),
				LocalDateTime.now());

		List<Item> itemList2 = Arrays.asList(item1, item2, item3);

		Order order2 = new Order(2, 20, itemList2, new BigDecimal("9.50"), StatusEnum.APPROVED, LocalDate.now(),
				LocalDateTime.now());

		List<Order> orderList = Arrays.asList(order1, order2);
		Order[] orders = restTemplate.getForObject("rest/consultapedidos", Order[].class);
		
        assertNotNull(orders);
        assertEquals(2, orders.length);


	}
}
