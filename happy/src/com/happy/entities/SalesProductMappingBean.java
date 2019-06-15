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
	@JoinColumn(name="item")
	private ProductBean product;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name="total")
	private Double productTotalAmt;

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

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getProductTotalAmt() {
		return productTotalAmt;
	}

	public void setProductTotalAmt(Double productTotalAmt) {
		this.productTotalAmt = productTotalAmt;
	}

}
