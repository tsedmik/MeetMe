package cz.muni.fi.pv243.meetme.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.meetme.security.HashPassword;

@Singleton
@Startup
public class DataDemoInitializer {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void initialize() throws Exception {
		
		//Users
		Person person1 = new Person("user1",HashPassword.byteArrayToHexString(HashPassword.computeHash("pass1")),"email1@email.com");
		Person person2 = new Person("user2",HashPassword.byteArrayToHexString(HashPassword.computeHash("pass2")),"email2@email.com");
		Person person3 = new Person("user3",HashPassword.byteArrayToHexString(HashPassword.computeHash("pass3")),"email3@email.com");
		Person person4 = new Person("user4",HashPassword.byteArrayToHexString(HashPassword.computeHash("pass4")),"email4@email.com");
		Person unknown = new Person("unknown", HashPassword.byteArrayToHexString(HashPassword.computeHash("234;lsl,.3Wr4")), "email4@email.com");
		manager.persist(person1);
		manager.persist(person2);
		manager.persist(person3);
		manager.persist(person4);
		manager.persist(unknown);
		
		//Groups
		List<Person> temp = new ArrayList<Person>();
		temp.add(person2);
		temp.add(person3);
		Party group1 = new Party("group1", "some notes", person1, temp);
		manager.persist(group1);
		
		//Events
		{
			Calendar from = new GregorianCalendar(2012, 5, 28, 10, 0);
			Calendar to = new GregorianCalendar(2012, 5, 28, 12, 0);
			Date date1 = new Date(from.getTime(), to.getTime(), null, null);
			from.set(2012, 5, 29, 10, 0);
			to.set(2012, 5, 29, 12, 0);
			Date date2 = new Date(from.getTime(), to.getTime(), null, null);
			List<Date> dates = new ArrayList<Date>();
			dates.add(date1);
			dates.add(date2);
			Event event1 = new Event("Příprava na zkoušku z Algebry", "Krátké setkání nad příklady z algebry", 
					"FI, počítačová studovna", person1, dates, CanParticipate.everyone, false, false, false, false);
			manager.persist(date1);
			manager.persist(date2);
			manager.persist(event1);
		}
		
		{
			Calendar from = new GregorianCalendar(2012, 6, 10, 12, 0);
			Calendar to = new GregorianCalendar(2012, 6, 10, 14, 0);
			Date date1 = new Date(from.getTime(), to.getTime(), null, null);
			from.set(2012, 6, 11, 12, 0);
			to.set(2012, 6, 11, 14, 0);
			Date date2 = new Date(from.getTime(), to.getTime(), null, null);
			List<Date> dates = new ArrayList<Date>();
			dates.add(date1);
			dates.add(date2);
			Event event1 = new Event("Den otevřených dveří", null, 
					"FI", person1, dates, CanParticipate.everyone, true, false, false, false);
			manager.persist(date1);
			manager.persist(date2);
			manager.persist(event1);
		}
		
		{
			Calendar from = new GregorianCalendar(2012, 6, 1, 8, 0);
			Calendar to = new GregorianCalendar(2012, 6, 1, 14, 0);
			Date date1 = new Date(from.getTime(), to.getTime(), null, null);
			from.set(2012, 6, 10, 10, 0);
			to.set(2012, 6, 10, 18, 0);
			Date date2 = new Date(from.getTime(), to.getTime(), null, null);
			List<Date> dates = new ArrayList<Date>();
			dates.add(date1);
			dates.add(date2);
			Event event1 = new Event("Přednáška na téma Enterprise Java", null, 
					"FI, D1", person1, dates, CanParticipate.regusers, true, false, false, true);
			manager.persist(date1);
			manager.persist(date2);
			manager.persist(event1);
		}
		

	}
}
