package com.happy.entities;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="happy", name="purchase_product_mapping")
public class PurchaseProductMappingBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purch_bill_id")
	private Integer mapping_id;
	
	@ManyToOne
	@JoinColumn(name="purchase)id")
	private PurchaseBean purchase;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private ProductBean product;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name="purch_rate")
	private Double purchaseRate;
	
	@Column(name="item_total")
	private Double itemTotal;
	
	@Column(name="delete_status")
	private String deleteStatus;

}
