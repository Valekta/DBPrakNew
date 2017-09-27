package db.dbPraktikum;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import db.model.Tag;

/**
 * Unit test for simple Tag.
 */
public class TagTest extends TestCase {
	private static SessionFactory sessionFactory;
	private static Transaction trx;
	private static Session session;
	private static SessionFactory factory;

	public void testApp() {

		/* Verbindung herstellen */
		try {
			System.out.println("Initializing Hibernate");

			sessionFactory = new Configuration().configure().buildSessionFactory();
			System.out.println("Finished Initializing Hibernate");
		} catch (HibernateException ex) {
			ex.printStackTrace();
			System.exit(5);
		}

		/* Speichern */
		try {
			session = sessionFactory.openSession();
			trx = session.beginTransaction();
			Tag tag = new Tag();
			session.save(tag);
			trx.commit();
		} catch (HibernateException ex) {
			if (trx != null)
				try {
					trx.rollback();
				} catch (HibernateException exRb) {
				}
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (Exception exCl) {
			}
		}
	}

	/* Method to READ all the Tags */
	public void listTag() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Employee").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Tag employee = (Tag) iterator.next();
				System.out.print("TID: " + employee.getId());
				System.out.print("Name: " + employee.getName());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}