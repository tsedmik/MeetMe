package cz.muni.fi.pv243.meetme.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatesItem {
	
	private String label;
	private String fromTime;
	private String toTime;
	private String fromDate;
	private String toDate;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DatesItem() {
		super();
	}
	
	public DatesItem(Date from, Date to, Long id, String label) {
		
		if (from==null || to==null) {
			return;
		}
		
		GregorianCalendar fromCalendar = new GregorianCalendar();
		GregorianCalendar toCalendar = new GregorianCalendar();
		
		fromCalendar.setTime(from);		
		toCalendar.setTime(to);
		fromDate = fromCalendar.get(Calendar.DATE) + "-" + (fromCalendar.get(Calendar.MONTH) + 1) + "-" + fromCalendar.get(Calendar.YEAR);
		fromTime = fromCalendar.get(Calendar.HOUR_OF_DAY) + ":" + fromCalendar.get(Calendar.SECOND);
		toDate = toCalendar.get(Calendar.DATE) + "-" + (toCalendar.get(Calendar.MONTH) + 1) + "-" + toCalendar.get(Calendar.YEAR);
		toTime = toCalendar.get(Calendar.HOUR_OF_DAY) + ":" + toCalendar.get(Calendar.SECOND);
		
		this.id = id;
		this.label = label;
	}
	
	public DatesItem(String label) {
		super();
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
