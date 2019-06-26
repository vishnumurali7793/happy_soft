package com.happy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="happy", name="sales_product_mapping")
public class SalesProductMappingBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapping_id")
	private Integer mappingId;
	
	@ManyToOne
	@JoinColumn(name="bill_id")
	private SalesBean salesBillId;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ProductBean productId;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name="item_total")
	private Double productTotalAmt;
	
	@Column(name="delete_status")
	private String deleteStatus="N";

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public SalesBean getSalesBillId() {
		return salesBillId;
	}

	public void setSalesBillId(SalesBean salesBillId) {
		this.salesBillId = salesBillId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public ProductBean getProductId() {
		return productId;
	}

	public Double getProductTotalAmt() {
		return productTotalAmt;
	}

	public void setProductId(ProductBean productId) {
		this.productId = productId;
	}

	public void setProductTotalAmt(Double productTotalAmt) {
		this.productTotalAmt = productTotalAmt;
	}

}
