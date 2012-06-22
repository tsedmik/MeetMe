package cz.muni.fi.pv243.meetme.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Class represents a group of users
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com
 * @since 03-05-2012
 */
@Entity
public class Party implements Serializable {

	private static final long serialVersionUID = -1885646994977096250L;

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Size(min = 4, max = 50)
	@Pattern(regexp = "[A-Za-z0-9]+", message = "must contain letters and numbers")
	private String name;
	
	@Size(max = 250)
	private String description;
	
	@ManyToOne
	@NotNull
	private Person owner;
	
	@ManyToMany
	@NotNull
	private List<Person> members;
	
	
	public Party() {
	}
	
	public Party(String name, String description, Person owner,
			List<Person> members) {
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.members = members;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public List<Person> getMembers() {
		return members;
	}
	public void setMembers(List<Person> members) {
		this.members = members;
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
		Party other = (Party) obj;
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
		return "Group [id=" + id + ", name=" + name + ", owner=" + owner + "]";
	}
}
