package com.happy.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="happy", name="sales")
public class SalesBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Integer billId;
	
	@Column(name="bill_date")
	private Date billDate;
	
	@Column(name="customer_id")
	private AccountHeadBean accountBean;
	
	@Column(name="discount_enabled")
	private String discountEnabled;
	
	@Column(name="discount")
	private Double discount;
	
	@Column(name="total_amt_before_discount")
	private Double totalBeforeDiscount;
	
	@Column(name="net_amount")
	private Double netAmount;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public AccountHeadBean getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(AccountHeadBean accountBean) {
		this.accountBean = accountBean;
	}

	public String getDiscountEnabled() {
		return discountEnabled;
	}

	public void setDiscountEnabled(String discountEnabled) {
		this.discountEnabled = discountEnabled;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotalBeforeDiscount() {
		return totalBeforeDiscount;
	}

	public void setTotalBeforeDiscount(Double totalBeforeDiscount) {
		this.totalBeforeDiscount = totalBeforeDiscount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

}
