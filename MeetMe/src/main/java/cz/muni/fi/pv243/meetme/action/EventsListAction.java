package cz.muni.fi.pv243.meetme.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.security.Identity;

import cz.muni.fi.pv243.meetme.model.Event;


@Stateless
@Named
public class EventsListAction {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	Identity identity;
	
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
	
	@Produces
    @Model
	public List<Event> getRegUserEvents() {
		
		if (!identity.isLoggedIn()) {
			return new ArrayList<Event>();
		}
		
		return em.createQuery("select b from Event b where b.owner.id=? order by b.name",
				Event.class).setParameter(1, identity.getUser().getId()).getResultList();
	}
	
}
