package cz.muni.fi.pv243.meetme.action;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.End;
import org.jboss.seam.international.status.Messages;
import org.jboss.solder.exception.control.ExceptionHandled;

import cz.muni.fi.pv243.meetme.annotation.Current;
import cz.muni.fi.pv243.meetme.model.Date;

@Named
@Stateless
public class EditEventAction {
	
	private ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Event<EventEditedEvent> eventEdited;
	
	@Inject
	@Current
	private cz.muni.fi.pv243.meetme.model.Event currentEvent;
	
	@Inject
	private Messages messages;
	
	@Inject
	private DatesList dates;

	@Begin
	public void edit() {
	}

	@End
	//@ExceptionHandled
	public void save() {
		
		// divide date into two groups - new one, and old
		List<DatesItem> oldDates = new ArrayList<DatesItem>();
		List<DatesItem> newDates = new ArrayList<DatesItem>();
		
		for (int i = 0; i < dates.getItems().size(); i++) {
			
			if (dates.getItems().get(i).getId() != null) {
					oldDates.add(dates.getItems().get(i));
			} else {
				if (!dates.getItems().get(i).getFromDate().equals(""))
					newDates.add(dates.getItems().get(i));
			}
		}
		
		System.out.println("OLDDATES: " + oldDates.size());
		System.out.println("NEWDATES: " + newDates.size());
		
		// change old dates in an event
		for (int i = 0; i < oldDates.size(); i++) {
			for (int j = 0; j < currentEvent.getDates().size(); j++) {
				if (oldDates.get(i).getId().equals(currentEvent.getDates().get(j).getId())) {
					
					if (oldDates.get(i).getFromDate().equals("")) {
						currentEvent.getDates().remove(j);
						break;
					}
					
					String[] tempDate = oldDates.get(i).getFromDate().split("-");
					String[] tempTime = oldDates.get(i).getFromTime().split(":");
					GregorianCalendar from = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
					tempDate = oldDates.get(i).getToDate().split("-");
					tempTime = oldDates.get(i).getToTime().split(":");
					GregorianCalendar to = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
					
					currentEvent.getDates().get(j).setFromDate(from.getTime());
					currentEvent.getDates().get(j).setToDate(to.getTime());
				}
			}
		}
		
		// add new dates to an event
		if (!newDates.isEmpty()) {
			List<Date> temp = createListOfDates(newDates);
			persistDates(temp);
			currentEvent.getDates().addAll(temp);		
		}
		
		System.out.println("DATES: " + currentEvent.getDates().size());
		
		// persist an event
		em.flush();
		eventEdited.fire(new EventEditedEvent());
		messages.info(msg.getString("editevent.changeEvent"), currentEvent.getName());
	}

	@End
	public void cancel() {
		messages.info(msg.getString("editevent.cancelEdit"), currentEvent.getName());
	}
	
	private List<Date> createListOfDates(List<DatesItem> dates) {
		
		if (dates == null) {
			return null;
		}
		
		List<Date> tempList = new ArrayList<Date>();
		
		for (int i = 0; i < dates.size(); i++) {
			
			// check if row is filled
			if (dates.get(i).getFromDate().equals("")) {
				continue;
			}
			
			String[] tempDate = dates.get(i).getFromDate().split("-");
			String[] tempTime = dates.get(i).getFromTime().split(":");
			GregorianCalendar from = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
			tempDate = dates.get(i).getToDate().split("-");
			tempTime = dates.get(i).getToTime().split(":");
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
