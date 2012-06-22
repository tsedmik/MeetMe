package cz.muni.fi.pv243.meetme.action;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.End;
import org.jboss.solder.exception.control.ExceptionHandled;

@Named
@Stateless
public class EditEventAction {
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Event<EventEditedEvent> eventEdited;

	@Begin
	public void edit() {
	}

	@End
	@ExceptionHandled
	public void save() {
		em.flush();
		eventEdited.fire(new EventEditedEvent());
		
		//TODO ukladani novych terminu a mazani smazanych
	}

	@End
	public void cancel() {

	}

}
