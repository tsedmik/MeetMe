package cz.muni.fi.pv243.meetme.action;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
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
import org.jboss.seam.security.Identity;

import cz.muni.fi.pv243.meetme.model.CanParticipate;
import cz.muni.fi.pv243.meetme.model.Date;
import cz.muni.fi.pv243.meetme.model.Event;
import cz.muni.fi.pv243.meetme.model.Person;

@Model
@Stateful
public class CreateEventAction {

	private ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private DatesList dates;
	
	@Inject
	private CurrentEventProducer eventProducer;
	
	@Inject
	private Identity identity;
	
	private Event event;
	
	
	@PostConstruct
	public void initialize() {
		event = new Event();
		
		// setting up attributes that are for unregistered users
		event.setCanParticipate(CanParticipate.onlywithlink);
		event.setEmailNotify(false);
		event.setOwner(em.createQuery("select b from Person b where b.username='unknown'",Person.class).getResultList().get(0));
		event.setSecretEvent(false);
	}
	
	@Inject
	Messages messages;
	
	public void create() {
		
		// link event with logged user
		if (identity.isLoggedIn()) {
			
			event.setOwner(em.createQuery("select b from Person b where b.id=?",Person.class).setParameter(1, identity.getUser().getId()).getResultList().get(0));
		}
		
		// data persist
		List<Date> tempDates = createListOfDates(dates);
		event.setDates(tempDates);
		persistDates(tempDates);
		em.persist(event);
		eventProducer.setEvent(event);
		
		// show message
		messages.info(msg.getString("event.eventCreated"), event.getName());
	}
	
	@Produces
	@Named
	public Event getNewEvent() {
		return event;
	}
	
	private List<Date> createListOfDates(DatesList dates) {
		
		if (dates == null) {
			return null;
		}
		
		List<Date> tempList = new ArrayList<Date>();
		
		for (int i = 0; i < dates.getItems().size(); i++) {
			
			// check if row is filled
			if (dates.getItems().get(i).getFromDate().equals("")) {
				continue;
			}
			
			String[] tempDate = dates.getItems().get(i).getFromDate().split("-");
			String[] tempTime = dates.getItems().get(i).getFromTime().split(":");
			GregorianCalendar from = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
			tempDate = dates.getItems().get(i).getToDate().split("-");
			tempTime = dates.getItems().get(i).getToTime().split(":");
			GregorianCalendar to = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
			
			Date date = new Date();
			date.setFromDate(from.getTime());
			date.setToDate(to.getTime());
			
			tempList.add(date);
		}
		
		// check if some row was filled
		if (tempList.isEmpty()) return null;
		
		return tempList;
	}
	
	private void persistDates(List<Date> dates) {
		
		if (dates == null) return;
		
		for (int i = 0; i < dates.size(); i++) {
			em.persist(dates.get(i));
		}
	}
	
}
