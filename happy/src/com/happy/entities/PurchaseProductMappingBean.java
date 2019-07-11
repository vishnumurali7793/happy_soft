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
@Table(schema = "happy", name = "purchase_product_mapping")
public class PurchaseProductMappingBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapping_id")
	private Integer mappingId;

	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private PurchaseBean purchase;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductBean product;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "purch_rate")
	private Double purchaseRate;

	@Column(name = "item_total")
	private Double itemTotal;

	@Column(name = "delete_status")
	private String deleteStatus="N";

	public Integer getMapping_id() {
		return mappingId;
	}

	public PurchaseBean getPurchase() {
		return purchase;
	}

	public ProductBean getProduct() {
		return product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public void setPurchase(PurchaseBean purchase) {
		this.purchase = purchase;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

}
