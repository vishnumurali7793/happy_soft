package com.happy.dao;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.happy.entities.AccountHeadBean;
import com.happy.entities.ProductBean;

public class MasterDao {
	

	public void addProduct(ProductBean productMaster) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(productMaster);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			session.close();
		}
	}

	public Integer getProductId() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (Integer) session.createQuery("select productId from ProductBean order by productId desc")
					.setMaxResults(1).uniqueResult();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 0;
		} finally {
			session.close();
		}

	}

	public void addAccountHead(AccountHeadBean head) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(head);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			session.close();
		}

	}

}
