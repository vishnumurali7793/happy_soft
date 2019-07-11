package com.happy.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="happy", name="daybook")
public class DayBookBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "db_id")
	private Integer dbId;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="entry_date")
	private Date entryDate;

	public Integer getDbId() {
		return dbId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setDbId(Integer dbId) {
		this.dbId = dbId;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

}
