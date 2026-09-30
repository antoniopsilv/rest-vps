package com.swagger.coderestapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.enums.StatusEnum;
import com.swagger.coderestapi.repository.RestApiRepository;

@Service
public class RestApiService {

	private final RestApiRepository restApiRepository;

	public RestApiService(RestApiRepository restApiRepository) {
		this.restApiRepository = restApiRepository;
	}

	public List<Order> getOrders() {
		return restApiRepository.findAll();
	}

	public Order createOrder(Order order) {
		return restApiRepository.save(order);
	}

	public Order updateOrder(Integer id, Order order) {

		Order existingOrder = restApiRepository.findOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));

	
	    existingOrder.setIdPartner(order.getIdPartner());
	    existingOrder.setItemList(order.getItemList());
	    existingOrder.setTotalValue(order.getTotalValue());
	    existingOrder.setStatus(order.getStatus());
	    existingOrder.setLastUpdateDate(LocalDateTime.now());

		return restApiRepository.save(existingOrder);
	}

	public Order cancelOrder(Integer id) {

		Order existingOrder = restApiRepository.findOrderById(id)
				.orElseThrow(() -> new RuntimeException("Order not found"));
		existingOrder.setStatus(StatusEnum.CANCELLED);
		
		return restApiRepository.save(existingOrder);
	}

	public Order findOrderById(Integer id) {

	    return restApiRepository.findOrderById(id)
	            .orElseThrow(() -> new RuntimeException("Order not found"));
	}

}
