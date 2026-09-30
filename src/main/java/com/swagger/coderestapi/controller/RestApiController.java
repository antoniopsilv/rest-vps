package com.swagger.coderestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.service.RestApiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/rest")
public class RestApiController {

	private final RestApiService restApiService;

	public RestApiController(RestApiService restApiService) {
		this.restApiService = restApiService;
	}

	@GetMapping("/consultapedidos")
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> order = restApiService.getOrders();
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/consultapedidos/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Integer id) {

	    Order order = restApiService.findOrderById(id);
	    return ResponseEntity.ok(order);
	}

	@PostMapping("/cadastrapedidos")
	public ResponseEntity<Order> createtOrder(@RequestBody Order order) {
		Order saveOrder = restApiService.createOrder(order);
		return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
	}

	@PutMapping("/atualizapedidos/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {

		Order updatedOrder = restApiService.updateOrder(id, order);
		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/cancelapedidos/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable Integer id) {

		Order cancelOrder = restApiService.cancelOrder(id);
		return ResponseEntity.ok(cancelOrder);
	}
}
