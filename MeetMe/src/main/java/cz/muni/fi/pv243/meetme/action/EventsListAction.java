package cz.muni.fi.pv243.meetme.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pv243.meetme.model.Event;


@Stateless
@Named
public class EventsListAction {
	
	@PersistenceContext
	private EntityManager em;
	
	@Produces
    @Model
	public List<Event> getEventsForEveryone() {

		return em.createQuery("select b from Event b where b.canParticipate=0 order by b.name",
				Event.class).getResultList();
	}
	
	@Produces
    @Model
	public List<Event> getEventsForRegUsers() {

		return em.createQuery("select b from Event b where b.canParticipate=1 order by b.name",
				Event.class).getResultList();
	}	
	
}
