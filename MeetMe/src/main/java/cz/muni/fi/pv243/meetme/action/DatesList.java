package cz.muni.fi.pv243.meetme.action;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Stateful
@ViewScoped
public class DatesList {
	
	private ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	private List<DatesItem> items = new ArrayList<DatesItem>();
	
	@Inject
	private CurrentEventProducer eventProducer;
	
	@PostConstruct
	public void initialize() {
		
		if (eventProducer.getEvent() != null) {
			for (int i = 0; i < eventProducer.getEvent().getDates().size(); i++) {
				items.add(new DatesItem(eventProducer.getEvent().getDates().get(i).getFromDate(),
										eventProducer.getEvent().getDates().get(i).getToDate(),
										eventProducer.getEvent().getDates().get(i).getId(),
										(i + 1) + msg.getString("newevent.dateOption")));
			}
		}
		else {
			for (int i = 1; i < 4; i++) {
				items.add(new DatesItem(i + msg.getString("newevent.dateOption")));
			}
		}
	}
	
	public void add() {
		
		for (int i = 1; i < 4; i++) {
			DatesItem temp = new DatesItem();
			temp.setLabel(items.size() + 1 + msg.getString("newevent.dateOption"));
			items.add(temp);
		}
	}
	
	public List<DatesItem> getItems() {
		return items;
	}
}
