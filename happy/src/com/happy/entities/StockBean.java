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
@Table(schema = "happy", name = "stock")
public class StockBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Integer stockId;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductBean productId;

	@Column(name = "stock")
	private Double stock;

	@Column(name = "delete_status")
	private String deleteStatus = "N";

	public Integer getStockId() {
		return stockId;
	}

	public ProductBean getProductId() {
		return productId;
	}

	public Double getStock() {
		return stock;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public void setProductId(ProductBean productId) {
		this.productId = productId;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

}
