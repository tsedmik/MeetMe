package cz.muni.fi.pv243.meetme.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.solder.core.Veto;

/**
 * Class represents an event
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com
 * @since 03-05-2012
 */
@Entity
@Veto
public class Event implements Serializable {

	private static final long serialVersionUID = 9140396113831392275L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min = 4, max = 100, message = "name - size must be between 4 and 100")
	private String name;
	
	@Size(max = 1000, message = "description - size must be max 100")
	private String description;
	
	@Size(max = 200, message = "description - size must be max 200")
	private String place;
	
	@ManyToOne
	private Person owner;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Date> dates;
	
	private CanParticipate canParticipate;
	
	// OPTIONAL
	private boolean answerMaybe;
	private boolean eventClose;
	private boolean secretEvent;
	private boolean emailNotify;
	
	
	public Event() {
	}
	
	public Event(String name, String description, String place, Person owner,
			List<Date> dates, CanParticipate canParticipate, boolean answerMaybe,
			boolean eventClose, boolean secretEvent, boolean emailNotify) {
		super();
		this.name = name;
		this.description = description;
		this.place = place;
		this.owner = owner;
		this.dates = dates;
		this.canParticipate = canParticipate;
		this.answerMaybe = answerMaybe;
		this.eventClose = eventClose;
		this.secretEvent = secretEvent;
		this.emailNotify = emailNotify;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	public boolean isAnswerMaybe() {
		return answerMaybe;
	}
	public void setAnswerMaybe(boolean answerMaybe) {
		this.answerMaybe = answerMaybe;
	}
	public boolean isEventClose() {
		return eventClose;
	}
	public void setEventClose(boolean eventClose) {
		this.eventClose = eventClose;
	}
	public boolean isSecretEvent() {
		return secretEvent;
	}
	public void setSecretEvent(boolean secretEvent) {
		this.secretEvent = secretEvent;
	}
	public boolean isEmailNotify() {
		return emailNotify;
	}
	public void setEmailNotify(boolean emailNotify) {
		this.emailNotify = emailNotify;
	}	
	public CanParticipate getCanParticipate() {
		return canParticipate;
	}

	public void setCanParticipate(CanParticipate canParticipate) {
		this.canParticipate = canParticipate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Event other = (Event) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", owner=" + owner + "]";
	}
}
