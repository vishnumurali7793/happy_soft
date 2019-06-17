package com.happy.action;

import com.happy.Ui.Sales;
import com.happy.dao.TransactionDao;
import com.happy.entities.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class TransactionAction {
	/*
	 * private static final String SUCCESS = "success"; private static final String
	 * FAILED = "failed";
	 */

	TransactionDao transactionDao = new TransactionDao();
	Sales sales = new Sales();

	public List<ProductBean> getProductList() {
		return transactionDao.getProductList();
	}

	public ProductBean getProductById(ProductBean bean) {
		return transactionDao.getProductById(bean);
	}
}
