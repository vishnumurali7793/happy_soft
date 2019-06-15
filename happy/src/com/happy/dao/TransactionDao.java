package com.happy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.happy.entities.ProductBean;

public class TransactionDao {

	@SuppressWarnings("unchecked")
	public List<ProductBean> getProductList() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return session.createQuery("from ProductBean").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
