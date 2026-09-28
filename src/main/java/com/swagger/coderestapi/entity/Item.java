package com.swagger.coderestapi.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

	private String product;
	private Integer quantity;
	private BigDecimal unitPrice;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(product, quantity, unitPrice);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(product, other.product) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(unitPrice, other.unitPrice);
	}

}
	