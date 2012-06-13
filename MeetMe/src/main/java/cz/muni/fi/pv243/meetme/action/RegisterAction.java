package cz.muni.fi.pv243.meetme.action;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;

import cz.muni.fi.pv243.meetme.model.Person;

@Model
@Stateful
public class RegisterAction {
	
	private ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	@PersistenceContext
	EntityManager em;
	
	private Person person;
	
	@PostConstruct
	public void initialize() {
		person = new Person();
	}
	
	@Inject
	Messages messages;
	
	public void create() {
		em.persist(person);
		messages.info(msg.getString("registration.newPersonCreated"), person.getId());
	}
	
	@Produces
	@Named
	public Person getNewPerson() {
		return person;
	}
}
