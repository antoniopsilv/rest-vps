package com.swagger.coderestapi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.swagger.coderestapi.enums.StatusEnum;

public class Order {

	private Integer id;
	private Integer idPartner;
	private List<Item> ItemList;
	private double totalValue;
	private StatusEnum status;
	private LocalDate createDate;
	private LocalDateTime lastUpdateDate;
	
	// Getters and Setters 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPartner() {
		return idPartner;
	}
	public void setIdPartner(Integer idPartner) {
		this.idPartner = idPartner;
	}
	public List<Item> getItemList() {
		return ItemList;
	}
	public void setItemList(List<Item> itemList) {
		ItemList = itemList;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ItemList, createDate, id, idPartner, lastUpdateDate, status, Double.valueOf(totalValue));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(ItemList, other.ItemList) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(id, other.id) && Objects.equals(idPartner, other.idPartner)
				&& Objects.equals(lastUpdateDate, other.lastUpdateDate) && status == other.status
				&& Double.doubleToLongBits(totalValue) == Double.doubleToLongBits(other.totalValue);
	}
	
}
