package db.util;

import db.model.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class StatisticImpl implements StatisticAPI {

	public void getTagClassHierarchy() {
		// TODO Auto-generated method stub
		
	}

	public void getPopularComments(int k) {
		// TODO Auto-generated method stub
		
	}

	public void getMostPostingCountry() {
        Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			// select * from country
			Query query = session.createQuery("from COUNTRY" );

			List<Country> country = (List<Country>) query.list();

			int max = 0;
			List<Country> countryMax = new ArrayList<Country>();

			for (Country t : country) {
				if (t.getMessagesById().size() > max) {
					max = t.getMessagesById().size();
					countryMax.clear();
					countryMax.add(t);
				} else if (t.getMessagesById().size() == max) {
					countryMax.add(t);

				}
			}
			for (Country c : countryMax) {
				System.out.println("getMostPostingCountry");
				System.out.println(c.getName() + " " + c.getMessagesById().size());
			}
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.print("\n");
			session.close();
		}
	}

}
