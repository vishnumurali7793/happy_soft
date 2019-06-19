package com.happy.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="happy", name="account_heads")
public class AccountHeadBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "head_id")
	private Integer headId;
	
	@Column(name="head_code")
	private String headCode;
	
	@Column(name="head_name")
	private String headName;
	
	@Column(name="head_phone")
	private String headPhone;
	
	@Column(name="head_account_no")
	private String headAccountNo;
	
	@Column(name="head_ifsc_code")
	private String headIfscCode;
	
	@Column(name="head_address")
	private String headAddress;
	
	@Column(name="head_pan_no")
	private String headPanNo;
	
	@Column(name="head_gstin")
	private String headGstin;
	
	@Column(name="head_type")
	private String headType;

	public Integer getHeadId() {
		return headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadPhone() {
		return headPhone;
	}

	public void setHeadPhone(String headPhone) {
		this.headPhone = headPhone;
	}

	public String getHeadAccountNo() {
		return headAccountNo;
	}

	public void setHeadAccountNo(String headAccountNo) {
		this.headAccountNo = headAccountNo;
	}

	public String getHeadIfscCode() {
		return headIfscCode;
	}

	public void setHeadIfscCode(String headIfscCode) {
		this.headIfscCode = headIfscCode;
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getHeadPanNo() {
		return headPanNo;
	}

	public void setHeadPanNo(String headPanNo) {
		this.headPanNo = headPanNo;
	}

	public String getHeadGstin() {
		return headGstin;
	}

	public void setHeadGstin(String headGstin) {
		this.headGstin = headGstin;
	}

	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getHeadCode() {
		return headCode;
	}

	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}

}
