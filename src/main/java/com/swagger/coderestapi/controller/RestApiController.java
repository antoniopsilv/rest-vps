package com.swagger.coderestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.service.RestApiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/rest")
public class RestApiController {

	private final RestApiService restApiService;
	
	public RestApiController(RestApiService restApiService) {
		this.restApiService =restApiService;
	}
	
	@GetMapping("/consultapedidos")
	public ResponseEntity<List<Order>> getOrders() {

		List<Order> order = restApiService.getOrders();
		return ResponseEntity.ok(order);

	}
	
	@PostMapping("/cadastropedidos")
	public ResponseEntity<String> insertOrder() {
		return new ResponseEntity<>("Cadastro de Pedidos da Api", HttpStatus.CREATED);
	}

	@PutMapping("/atualizarpedidos")
	public ResponseEntity<Order> updateOrder() {
		return new ResponseEntity<Order>(new Order(null, null, null, null, null, null, null), HttpStatus.OK);
	}
	
	@DeleteExchange("/cancelarpedidos")
	public ResponseEntity<String> cancelOrder() {
		return new ResponseEntity("Pedido Cancelado", HttpStatus.OK);
	}
}
