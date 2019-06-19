package com.happy.action;

import com.happy.Ui.Sales;
import com.happy.dao.TransactionDao;
import com.happy.entities.AccountHeadBean;
import com.happy.entities.ProductBean;
import com.happy.entities.SalesBean;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TransactionAction {

	private static final String SUCCESS = "success";
	private static final String FAILED = "failed";

	Sales sales;
	TransactionDao transactionDao = new TransactionDao();
	AccountHeadBean headBean = new AccountHeadBean();

	public List<ProductBean> getProductList() {
		return transactionDao.getProductList();
	}

	public ProductBean getProductById(ProductBean bean) {
		return transactionDao.getProductById(bean);
	}

	public double calculateTotal(double quantity, double price) {
		if (quantity > 0 && price > 0) {
			return quantity * price;
		}
		return 0;
	}

	public ArrayList<AccountHeadBean> getAccountHeads() {
		return transactionDao.getAccountHeads();
	}

	public void getHeadById(int headId) {
		if (headId > 0) {
			headBean = transactionDao.getHeadById(headId);
		} else {
			JOptionPane.showMessageDialog(null, "Some error occured");
		}

		if (headBean != null) {
			sales = new Sales();
			sales.setVisible(true);
			sales.setCustName(headBean.getHeadName());
			sales.setCustAddress(headBean.getHeadAddress());
			sales.setCustPhone(headBean.getHeadPhone());
			sales.setCustHeadCode(headBean.getHeadCode());
			sales.refresh();
		}

	}

	public int getSalesCount() {
		return transactionDao.getSalesCount();
	}

	public AccountHeadBean getHeadsByHeadCode(String accountBean) {
		return transactionDao.getHeadsByHeadCode(accountBean);
	}

	public String saveSalesBill(SalesBean salesBean) {
		int status = transactionDao.saveSalesBill(salesBean);
		if (status > 0) {
			return SUCCESS;
		} else if (status <= 0) {
			return FAILED;
		}
		return null;

	}
}
