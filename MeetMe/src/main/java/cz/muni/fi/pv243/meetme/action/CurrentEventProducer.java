package cz.muni.fi.pv243.meetme.action;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.meetme.model.Event;

@ConversationScoped
@Named
@Stateful
public class CurrentEventProducer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	CurrentEventUnwrapper eventUnwrapper;
		
	@PersistenceContext
	EntityManager em;
	 
	public void setEvent(Event event) {
		eventUnwrapper.setCurrentEvent(event);
	}
		
	public Event getEvent() {
		return eventUnwrapper.getCurrentEvent();
	}
		
	public void setEventById(String eventId) {
		eventUnwrapper.setCurrentEvent(em.find(Event.class, Long.parseLong(eventId)));
	}
		
	public String getEventById() {
		if (eventUnwrapper.getCurrentEvent() == null) {
			return null;
		}
		
		return eventUnwrapper.getCurrentEvent().getId().toString();
	}	

}
