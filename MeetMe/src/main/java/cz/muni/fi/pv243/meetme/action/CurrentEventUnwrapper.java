package cz.muni.fi.pv243.meetme.action;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.jboss.solder.unwraps.Unwraps;

import cz.muni.fi.pv243.meetme.annotation.Current;
import cz.muni.fi.pv243.meetme.model.Event;

@ConversationScoped
public class CurrentEventUnwrapper implements Serializable {
	
	private Event event;
	
	public void setCurrentEvent(Event event) {
		this.event = event;
	}
	   
	public Event getCurrentEvent() {
		return event;
	}
	   
	@Unwraps
	@Current
	@Named("event")
	public Event produceCurrentBakery() {
		return event;
	}

}
