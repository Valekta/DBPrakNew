package db.util;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import db.model.*;

public class PersonRelatedImpl implements PersonRelatedAPI {

	public void getProfile(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query q = session.createQuery("select p from Person p where p.pid = :id");
			q.setParameter("id", id);
			Person person = (Person) q.getSingleResult();
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			System.out.println("getProfil");
			System.out.print("Name: " + person.getFirstName() + " " + person.getLastName() + "\n" 
					+ "Birthday: " + df.format(person.getBirthday().getTime()) + "\n" + "Gender: " + person.getGender() + "\n" 
					+ "Location: " + person.getLocatedIn().getName() + ", " + person.getLocatedIn().getCountry().getName() + " ("
					+ person.getLocatedIn().getCountry().getContinent().getName() + ")\n" +
					"Languages: ");
			for(String language : person.getLanguages()) {
		    	System.out.print(language + "|");
		    }
			System.out.println("");
			System.out.print("Email: ");
			for(String email : person.getEmails()) {
		    	System.out.print(email + "|");
		    }
			System.out.println("");
			System.out.println("Join: " + person.getCreationDate().getTime());
		} catch(Exception e) {
				System.out.println(e);
		} finally {
			System.out.print("\n");
			session.close();
		}
	}

	public void getCommonInterestsOfMyFriends(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query q = session.createQuery("select p from Person p where p.pid = :id");
			q.setParameter("id", id);
			Person person = (Person) q.getSingleResult();
			System.out.println("getCommonInterestsOfMyFriends");
			Set<Tag> friendsInterests = new HashSet<Tag>();
			for (Iterator<Person> it = person.getFriends().keySet().iterator(); it.hasNext(); ) {
				Person friend = it.next();
				if(friend.getPid() != person.getPid()) {
					friendsInterests.addAll(friend.getInterests());
				}
			}
			for(Tag interest : friendsInterests) {
				if(person.getInterests().contains(interest))
					System.out.println("Id: " + interest.getId() + " Name: " + interest.getName());
			}
		} catch(Exception e) {
				System.out.println(e);
		} finally {
			System.out.print("\n");
			session.close();
		}
		
	}

	public void getCommonFriends(Long id1, Long id2) {
		// TODO Auto-generated method stub
		
	}

	public void getPersonsWithMostCommonInterests(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void getJobRecommendation(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void getShortestFriendshipPath(Long id1, Long id2) {
		// TODO Auto-generated method stub
		
	}

}
