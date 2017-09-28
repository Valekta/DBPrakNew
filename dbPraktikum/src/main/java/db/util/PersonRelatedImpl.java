package db.util;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import db.model.*;

public class PersonRelatedImpl implements PersonRelatedAPI {

	public void getProfile(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Person person = selectPersonById(session, id);
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
			Person person = selectPersonById(session, id);
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Person person1 = selectPersonById(session, id1);
			Person person2 = selectPersonById(session, id2);
			System.out.println("getCommonInterestsOfMyFriends");
			for (Iterator<Person> it = person1.getFriends().keySet().iterator(); it.hasNext(); ) {
				Person friend = it.next();
				if(friend.getPid() != person1.getPid() && person2.getFriends().containsKey(friend)) {
					System.out.println("Id: " + friend.getPid() + " Name: " + friend.getFirstName() + friend.getLastName());
				}
			}
		} catch(Exception e) {
				System.out.println(e);
		} finally {
			System.out.print("\n");
			session.close();
		}
	}

	public void getPersonsWithMostCommonInterests(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Person person = selectPersonById(session, id);
			Query q = session.createQuery("select p from Person p where p.pid != :id");
			q.setParameter("id", id);
			List<Person> personen = (List<Person>)q.getResultList();
			System.out.println("getPersonsWithMostCommonInterests");
			int mostCommonInterests = 0;
			Set<Person> personWithMostCommonInterests = new HashSet<Person>();

			for (Person p : personen) {
				Set<Tag> tags = new HashSet<Tag>();
				tags.addAll(person.getInterests());
				tags.retainAll(p.getInterests());
				if (tags.size() > mostCommonInterests) {
					mostCommonInterests = tags.size();
					personWithMostCommonInterests.clear();
					personWithMostCommonInterests.add(p);
				} else if (tags.size() == mostCommonInterests) {
					personWithMostCommonInterests.add(p);
				}
			}
			System.out.println("Gemeinsame Interessen: " + mostCommonInterests);
			for (Person p : personWithMostCommonInterests) {
				System.out.println("Id: " + p.getPid() + " Name: " + p.getFirstName() + " " + p.getLastName());
			}
		} catch(Exception e) {
				System.out.println(e);
		} finally {
			System.out.print("\n");
			session.close();
		}
	}

	public void getJobRecommendation(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void getShortestFriendshipPath(Long id1, Long id2) {
		// TODO Auto-generated method stub
		
	}

	private Person selectPersonById(Session session, Long id) {
		Query q = session.createQuery("select p from Person p where p.pid = :id");
		q.setParameter("id", id);
		Person person = (Person) q.getSingleResult();
		return person;
	}
}
