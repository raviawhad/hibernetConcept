package com.jbk.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jbk.config.HibernateConfiguration;
import com.jbk.entity.Product;

public class ProductDao {
	SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

	public String addProduct(Product product) {

		String msg = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.save(product);
			session.beginTransaction().commit();
			msg = "Added";

		} catch (Exception e) {
			msg = "Product already Exists with id= " + product.getProductId();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;

	}

	public Product getProductById(long productId) {
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();

			product = session.get(Product.class, productId); // alt+shift+L

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return product;

	}

	public String deleteProductById(long productId) {
		Session session = null;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			Product product = getProductById(productId);
			if(product!=null) {
				session.delete(product);
				session.beginTransaction().commit();
				msg="Product Deleted";
			}else {
				msg="Product Not found With Id = "+productId;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}

		return msg;

	}

}
