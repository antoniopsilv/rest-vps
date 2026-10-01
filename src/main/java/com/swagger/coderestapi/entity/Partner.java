package com.swagger.coderestapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "partners")
public class Partner implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8703951362708898598L;

	@Id
    private String mongoId;

    @Indexed(unique = true)
    private Integer id;

    private BigDecimal creditLimit;

    private BigDecimal availableCredit;

    // constructor
    public Partner() {
    }

    public Partner(Integer id, BigDecimal creditLimit) {
        this.id = id;
        this.creditLimit = creditLimit;
        this.availableCredit = creditLimit;
    }

    // getters e setters
	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getAvailableCredit() {
		return availableCredit;
	}

	public void setAvailableCredit(BigDecimal availableCredit) {
		this.availableCredit = availableCredit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availableCredit, creditLimit, id, mongoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partner other = (Partner) obj;
		return Objects.equals(availableCredit, other.availableCredit) && Objects.equals(creditLimit, other.creditLimit)
				&& Objects.equals(id, other.id) && Objects.equals(mongoId, other.mongoId);
	}


    
}