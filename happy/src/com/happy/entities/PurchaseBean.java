package com.happy.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="happy", name="purchase")
public class PurchaseBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purch_bill_id")
	private Integer pBillId;
	
	@Column(name="purch_bill_no")
	private String billNo;
	
	@Column(name="purch_bill_date")
	private Date billDate;
	
	@Column(name="purchase_date")
	private Date purchDate;
	
	@Column(name="discount")
	private Double discount;
	
	@Column(name="discount_enabled")
	private String discountEnabled;
	
	@Column(name="sub_total")
	private Double subTotal;
	
	@Column(name="net_amount")
	private Double netAmount;
	
	@ManyToOne
	@JoinColumn(name="head_id")
	private AccountHeadBean supplierHeadId;
	
	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name="delete_status")
	private String deleteStatus="N";

	public Integer getpBillId() {
		return pBillId;
	}

	public String getBillNo() {
		return billNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public Date getPurchDate() {
		return purchDate;
	}

	public Double getDiscount() {
		return discount;
	}

	public String getDiscountEnabled() {
		return discountEnabled;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public AccountHeadBean getSupplierHeadId() {
		return supplierHeadId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setpBillId(Integer pBillId) {
		this.pBillId = pBillId;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setDiscountEnabled(String discountEnabled) {
		this.discountEnabled = discountEnabled;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public void setSupplierHeadId(AccountHeadBean supplierHeadId) {
		this.supplierHeadId = supplierHeadId;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	
}
