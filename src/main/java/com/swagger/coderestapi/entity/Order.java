package com.swagger.coderestapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.swagger.coderestapi.enums.StatusEnum;

@Document(collection = "orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7125376740709495547L;

	@Id
	private String mongoId;
	
	@Indexed(unique = true)
	private Integer id;
	
	private Integer idPartner;
	
	private List<Item> itemList;
	
	private BigDecimal totalValue;
	
	private StatusEnum status;
	
	private LocalDate createDate;
	
	private LocalDateTime lastUpdateDate;

	public Order() {
	}

	public Order(Integer id, Integer idPartner, List<Item> itemList, BigDecimal totalValue, StatusEnum status,
			LocalDate createDate, LocalDateTime lastUpdateDate) {
		this.id = id;
		this.idPartner = idPartner;
		this.itemList = itemList;
		this.totalValue = totalValue;
		this.status = status;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}

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
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
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
		return Objects.hash(createDate, id, idPartner, itemList, lastUpdateDate, status, totalValue);
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
		return Objects.equals(createDate, other.createDate) && Objects.equals(id, other.id)
				&& Objects.equals(idPartner, other.idPartner) && Objects.equals(itemList, other.itemList)
				&& Objects.equals(lastUpdateDate, other.lastUpdateDate) && status == other.status
				&& Objects.equals(totalValue, other.totalValue);
	}

}
