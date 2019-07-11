package com.happy.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.happy.entities.AccountHeadBean;
import com.happy.entities.DayBookBean;
import com.happy.entities.ProductBean;
import com.happy.entities.PurchaseBean;
import com.happy.entities.PurchaseProductMappingBean;
import com.happy.entities.SalesBean;
import com.happy.entities.SalesProductMappingBean;
import com.happy.entities.StockBean;

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
			return ((BigInteger) session.createSQLQuery("SELECT count(*) FROM sales").uniqueResult()).intValue();
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
			return (AccountHeadBean) session.createQuery("from AccountHeadBean where headCode=:headCode")
					.setParameter("headCode", accountBean).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	public int saveSalesBill(SalesBean salesBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (int) session.save(salesBean);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}

	}

	public int getProductIdByCode(String itemCode) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (int) session.createQuery("select productId from ProductBean where productCode=:productCode")
					.setParameter("productCode", itemCode).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public int saveSalesProductMapping(SalesProductMappingBean spMappingBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (int) session.save(spMappingBean);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public AccountHeadBean getHeadDetails(Integer headId) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (AccountHeadBean) session.createQuery("from AccountHeadBean where headId=:headId")
					.setParameter("headId", headId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SalesProductMappingBean> getSalesItemList(SalesProductMappingBean mapBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return session.createQuery("from SalesProductMappingBean where salesBillId=:billId")
					.setParameter("billId", mapBean.getSalesBillId()).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void savePurchase(PurchaseBean purchaseBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(purchaseBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void savePurchaseProduct(PurchaseProductMappingBean purchProductMappingBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(purchProductMappingBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void saveDayBookEntry(DayBookBean dayBookBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(dayBookBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateStock(Integer productId, Double quantity) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			session.createQuery("UPDATE StockBean SET stock=:quantity WHERE productId.productId=:productId")
					.setParameter("quantity", quantity).setParameter("productId", productId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public ProductBean getProductByItemCode(String itemCode) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (ProductBean) session.createQuery("FROM ProductBean WHERE productCode=:itemCode")
					.setParameter("itemCode", itemCode).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public StockBean getStockByProductId(Integer productId) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			return (StockBean) session.createQuery("From StockBean WHERE productId.productId=:productId")
					.setParameter("productId", productId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
