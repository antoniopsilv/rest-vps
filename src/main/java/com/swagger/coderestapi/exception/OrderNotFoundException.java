package com.swagger.coderestapi.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8905750204413355977L;
	
    public OrderNotFoundException(Integer id) {
        super("Pedido com ID: " + id + "não encontrado");
    }



}
