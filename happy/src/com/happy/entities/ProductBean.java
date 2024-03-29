package com.happy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema="happy", name="product")
public class ProductBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name="item_code")
	private String productCode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_cat")
	private String productType;
	
	@Column(name="sell_unit")
	private String sellingUnit;
	
	@Column(name="sell_price")
	private Double sellingPrice;
	
	@Column(name="delete_status")
	private String deleteStatus="N";
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getSellingUnit() {
		return sellingUnit;
	}
	
	public void setSellingUnit(String sellingUnit) {
		this.sellingUnit = sellingUnit;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
