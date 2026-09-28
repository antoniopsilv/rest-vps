package com.swagger.coderestapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

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
	
	public Order retrieveOrder() {
		
		Item item1 = new Item("Laranja", 12, new BigDecimal("1.00"));
		Item item2 = new Item("Banana", 12, new BigDecimal("1.00"));
		Item item3 = new Item("Maca", 12, new BigDecimal("1.00"));

		List<Item> itemList = Arrays.asList(item1, item2, item3);

		return new Order(1, 10, itemList, new BigDecimal("7.50"), StatusEnum.APPROVED, LocalDate.now(),
				LocalDateTime.now());
	}

}
