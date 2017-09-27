package db.dbPraktikum;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import db.model.City;
import db.model.Continent;
import db.model.Country;

/**
 * Unit test for Place Hierarchy.
 */
public class PlacesTest extends TestCase {
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
			Continent asia = new Continent(1, "asia");
			Country japan = new Country(2, "japan", asia);
			City yokohama = new City(1, "yokohama", japan);
			session.save(yokohama);

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