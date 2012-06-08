package cz.muni.fi.pv243.meetme.action;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.meetme.model.Person;

@Stateless
@Named
public class PersonManagerImpl implements PersonManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Produces
    @Model
	public List<Person> getAllPersons() {

		return em.createQuery("select b from Person b order by b.username",
				Person.class).getResultList();
	}

}
