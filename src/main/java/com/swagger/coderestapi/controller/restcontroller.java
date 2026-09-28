package com.swagger.coderestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rest")
public class restcontroller {

	@PostMapping("/cadastropedidos")
	public ResponseEntity<String> cadastrarPedidos() {
		return new ResponseEntity<>("Cadastro de Pedidos da Api", HttpStatus.OK);
	}

}
