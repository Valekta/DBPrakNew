package db.model;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Unit test for Organisation Hierarchy.
 */
public class PersonTest extends TestCase {
	private static SessionFactory sessionFactory;
	private static Transaction trx;
	private static Session session;
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
			Continent europa = new Continent(1, "Europa");
			Country deutschland = new Country(2, "Deutschland", europa);
			City leipzig = new City(1, "Leipzig", deutschland);
			University UniLeipzig = new University(1, "Universitaet Leipzig", leipzig);
			Company ewerk = new Company(1, "EWERK", deutschland);
			Person person = new Person();
			session.save(person);
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
}