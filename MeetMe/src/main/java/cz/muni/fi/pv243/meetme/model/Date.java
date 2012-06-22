package cz.muni.fi.pv243.meetme.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Class represents a single date
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com
 * @since 03-05-2012
 */
@Entity  
public class Date implements Serializable {

	private static final long serialVersionUID = 5406244067831461969L;

	@Id 
	@GeneratedValue
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fromDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date toDate;
	
	@Size(max = 200)
	private String note;
	
	@OneToMany
	private List<Response> responses;
	
	
	public Date() {
	}
	
	public Date(java.util.Date fromDate, java.util.Date toDate, String note,
			List<Response> responses) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.note = note;
		this.responses = responses;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public java.util.Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(java.util.Date fromDate) {
		this.fromDate = fromDate;
	}
	public java.util.Date getToDate() {
		return toDate;
	}
	public void setToDate(java.util.Date toDate) {
		this.toDate = toDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result
				+ ((responses == null) ? 0 : responses.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (responses == null) {
			if (other.responses != null)
				return false;
		} else if (!responses.equals(other.responses))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf= new SimpleDateFormat("dd-M-yy hh:ss");
		return sdf.format(fromDate) + " - " + sdf.format(toDate);
	}
	


}
