package com.happy.dao;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.happy.entities.LoginBean;


public class LoginDao {
	
	
	public LoginBean authenticateUser(LoginBean login) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (LoginBean)session.createQuery("from LoginBean WHERE userName=:username AND password=:password")
			.setParameter("username", login.getUserName())
			.setParameter("password", login.getPassWord())
			.uniqueResult();
			
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}finally {
			session.close();
		}
	}

}
