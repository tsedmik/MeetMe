package cz.muni.fi.pv243.meetme.action;

public class DatesItem {
	
	private String label;
	private String fromTime;
	private String toTime;
	private String fromDate;
	private String toDate;
	
	public DatesItem() {
		super();
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
