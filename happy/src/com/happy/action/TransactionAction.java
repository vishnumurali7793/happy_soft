package com.happy.action;

import com.happy.dao.TransactionDao;
import com.happy.entities.ProductBean;

import java.util.List;

public class TransactionAction {

/*	private static final String SUCCESS = "success";
	private static final String FAILED = "failed";*/

	TransactionDao transactionDao = new TransactionDao();

/*	private ArrayList<ProductBean> productList;*/

	public List<ProductBean> getProductList() {
		return transactionDao.getProductList();
	}
}
