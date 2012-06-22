package cz.muni.fi.pv243.meetme.action;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.End;
import org.jboss.solder.exception.control.ExceptionHandled;

@Named
@Stateless
public class EditEventAction {
	
	@PersistenceContext
	private EntityManager em;

	@Begin
	public void edit() {
	}

	@End
	@ExceptionHandled
	public void save() {
		em.flush();
	}

	@End
	public void cancel() {

	}

}
