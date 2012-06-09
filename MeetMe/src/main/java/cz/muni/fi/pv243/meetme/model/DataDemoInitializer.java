package cz.muni.fi.pv243.meetme.model;

import java.util.ArrayList;
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
		manager.persist(person1);
		manager.persist(person2);
		manager.persist(person3);
		manager.persist(person4);
		
		//Groups
		List<Person> temp = new ArrayList<Person>();
		temp.add(person2);
		temp.add(person3);
		Party group1 = new Party("group1", "some notes", person1, temp);
		manager.persist(group1);
	}
}
