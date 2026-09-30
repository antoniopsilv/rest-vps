package com.swagger.coderestapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.swagger.coderestapi.entity.Item;
import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.enums.StatusEnum;
import com.swagger.coderestapi.exception.OrderNotFoundException;


@Service
public class RestApiService {

	
//    private final RestApiRepository restApiRepository;
//
//    public RestApiService(RestApiRepository restApiRepository) {
//        this.restApiRepository = restApiRepository;
//    }
//
//    public Order retrieveOrder(Integer id) {
//
//        return restApiRepository.findById(id)
//            .orElseThrow(() -> new OrderNotFoundException(id));
//    }
	
	
	public List<Order> getOrders() {
		
		Item item1 = new Item("Laranja", 12, new BigDecimal("1.00"));
		Item item2 = new Item("Banana", 12, new BigDecimal("1.00"));
		Item item3 = new Item("Maca", 12, new BigDecimal("1.00"));

		List<Item> itemList1 = Arrays.asList(item1, item2, item3);
		
	    Order order1 = new Order(
	            1,
	            10,
	            itemList1,
	            new BigDecimal("7.50"),
	            StatusEnum.APPROVED,
	            LocalDate.now(),
	            LocalDateTime.now()
	    );

		Item item4 = new Item("Pera", 12, new BigDecimal("1.00"));
		Item item5 = new Item("Abacate", 12, new BigDecimal("1.00"));
		Item item6 = new Item("Kiwi", 12, new BigDecimal("1.00"));

		List<Item> itemList2 = Arrays.asList(item1, item2, item3);
	    
	    Order order2 = new Order(
	            2,
	            20,
	            itemList2,
	            new BigDecimal("9.50"),
	            StatusEnum.APPROVED,
	            LocalDate.now(),
	            LocalDateTime.now()
	    );
	    
	    
	    return Arrays.asList(order1,order2);
	}


}
