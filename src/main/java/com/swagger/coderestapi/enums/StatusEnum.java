package com.swagger.coderestapi.enums;

public enum StatusEnum {
	
	PENDING("PENDENTE"), 
	APPROVED("APROVADO"), 
	PROCESSING("EM_PROCESSAMENTO"), 
	SHIPPED("ENVIADO"), 
	DELIVERED("ENTREGUE"), 
	CANCELLED("CANCELADO");

	private String status;
	
	StatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
}
