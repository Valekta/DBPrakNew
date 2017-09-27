package db.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * Nicht besonders schöne Testklasse, aber für mehr war keine Zeit
 */
public class Test {

	public static void main(String[] args) {
		int aufrufe = 0;
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		City city = (City)session.createQuery("from City" ).list().get(0);
		Country country = (Country)session.createQuery("from Country" ).list().get(0);
		Company company = (Company)session.createQuery("from Company" ).list().get(0);
		University university = (University)session.createQuery("from University" ).list().get(0);
		Comment comment = (Comment)session.createQuery("from Comment" ).list().get(0);
		
		Calendar datum = new GregorianCalendar();
		Person perso = new Person(Long.parseLong("24"), datum, "Lisaaa", "Vogelsberg", "192.187.244.1", city);
		Post post = new Post(Long.parseLong("11"),new GregorianCalendar(), "192.198.123.1", perso, country,(Forum)session.createQuery("from Forum" ).list().get(0));
		post.setContent("TEST 111");
		session.persist(post);
		perso.addEmail("karuri@web.de");
		perso.addLanguage("de");
		perso.addFriend((Person)session.createQuery("from Person" ).list().get(0));
		//CompanyMembershipPK cms_pk = new CompanyMembershipPK(perso, company);
		//perso.addCompanyMembership(new CompanyMembership(cms_pk, 2017));
		//UniversityMembershipPK ums_pk = new UniversityMembershipPK(perso, university);
		//perso.addUniMembership(new UniversityMembership(ums_pk, 2017));
		perso.addInterest(new Tag(17000, "Blubb"));
		perso.addCommentLike(comment);
		//perso.addPostLike((Post)session.createQuery("from Post" ).list().get(0));
		Comment comment2 = new Comment(Long.parseLong("12"),new GregorianCalendar(), "192.198.123.1", perso, country);
		comment2.setReplyOfComment(comment);
		session.persist(perso);
		session.persist(comment2);
		
		/* Forum ausgabe tests
		session.beginTransaction();
		List<Forum> result = session.createQuery("from Forum" ).list();
		for (Forum forum : result ) {
		    System.out.println( "Event (" + forum.getId() + ") : " + forum.getTitle() + ", "  + forum.getCreationDate().getWeekYear() + ", " + forum.getModerator().getLastName());
		    for(Map.Entry<Person, Calendar> cm : forum.getMembers().entrySet()) {
		    	System.out.print(cm.getKey().getPid() + ", ");
		    }
		    System.out.println(" ENDE");
		    for(Post post : forum.getPosts()) {
		    	System.out.print(post.getId() + ", " + post.getForum().getId() +  " , " + post.getContent() + " || ");
		    }
		    System.out.println(" ENDE");
		    for(Tag tag : forum.getTags()) {
		    	System.out.print(tag.getName() + ", ");
		    }
		    System.out.println(" ENDE");
		    aufrufe++;
		}
		System.out.println(aufrufe);
		*/
		
		/*Person ausgabe tests
		session.beginTransaction();
		List<Person> result = session.createQuery("from Person" ).list();
		for (Person person : result ) {
		    System.out.println( "Event (" + person.getPid() + ") : " + person.getCreationDate().getWeekYear() + ", "  + person.getFirstName() + ", " + person.getLastName() + ", " + person.getGender() + ", " + person.getBirthday().getWeekYear() + ", " + person.getBrowserUsed() + ", " + person.getLocationIP() + ", " + person.getLocatedIn().getName());
		
		    for(UniversityMembership cm : person.getUniMemberships()) {
		    	System.out.print(cm.getStudent().getLastName() + ", " + cm.getUniversity().getName() +  " , " + cm.getClassYear());
		    }
		
		    for(CompanyMembership cm : person.getCompanyMemberships()) {
		    	System.out.print(cm.getEmployee().getLastName() + ", " + cm.getCompany().getName() +  " , " + cm.getWorkFrom());
		    }
		
		    for(String mail : person.getEmails()) {
		    	System.out.print(mail + ", ");
		    }
		    for(String language : person.getLanguages()) {
		    	System.out.print(language + ", ");
		    }
	
		    for(Map.Entry<Person, Calendar> cm : person.getFriends().entrySet()) {
		    	System.out.print(cm.getKey().getPid() + ", ");
		    }
		    System.out.println(" ENDE");
		    aufrufe++;
		}
		System.out.println(aufrufe);
		*/
		aufrufe = 0;
		System.out.println("Test1");
		session.getTransaction().commit();
		System.out.println("Test2");
		session.close();
		System.out.println("Test3");
	}
}
