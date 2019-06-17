package com.happy.action;


import com.happy.dao.MasterDao;
import com.happy.entities.AccountHeadBean;

public class MasterAction {
	
	static final String SUCCESS = "success";
	static final String FAILED = "failed";
	
	MasterDao dao = new MasterDao();
	
	public String saveAccountHead(AccountHeadBean head) {
		
		if(head!=null) {
			dao.addAccountHead(head);
		}else {
			return FAILED;
		}
		return SUCCESS;	
	}

}
