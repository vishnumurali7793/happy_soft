package com.happy.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.happy.entities.AccountHeadBean;
import com.happy.entities.ProductBean;
import com.happy.entities.SalesBean;

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

	public ProductBean getProductById(ProductBean bean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (ProductBean) session.createQuery("from ProductBean where productId=:productId")
					.setParameter("productId", bean.getProductId()).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public ArrayList<AccountHeadBean> getAccountHeads() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (ArrayList<AccountHeadBean>) session.createQuery("from AccountHeadBean").getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}

	}

	public AccountHeadBean getHeadById(int headId) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (AccountHeadBean) session.createQuery("from AccountHeadBean where headId=:headId")
					.setParameter("headId", headId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getSalesCount() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (Integer) session.createSQLQuery("SELECT count(bill_id) FROM sales").uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public AccountHeadBean getHeadsByHeadCode(String accountBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (AccountHeadBean)session.createQuery("from AccountHeadBean where headCode=:headCode")
					.setParameter("headCode", accountBean).uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

	public int saveSalesBill(SalesBean salesBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (int) session.save(salesBean);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally {
			session.close();
		}
		
	}

}
