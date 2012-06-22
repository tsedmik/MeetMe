package cz.muni.fi.pv243.meetme.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.picketlink.idm.api.User;

import cz.muni.fi.pv243.meetme.security.HashPassword;;

/**
 * Class represents a user
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com
 * @since 03-05-2012
 */
@Entity
public class Person implements User, Serializable {

	private static final long serialVersionUID = 2677466923821176439L;

	@Id
	@NotNull
	@Size(min = 4, max = 25, message = "username - size must be between 4 and 25")
	@Pattern(regexp = "[A-Za-z0-9]+", message = "username - must contain letters and numbers")
	private String username;

	@NotNull
	@Size(min = 4, max = 50, message = "password - size must be between 4 and 50")
	private String password;

	@Pattern(regexp = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})", message = "email - must be a correct email address")
	private String email;

	@Size(max = 100, message = "name - size must be between 0 and 100")
	private String name;

	public Person() {
	}

	public Person(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {

		this.password = HashPassword.byteArrayToHexString(HashPassword.computeHash(password));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Person other = (Person) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", email=" + email + "]";
	}

	@Override
	@Transient
	public String getKey() {
		return getId();
	}

	@Override
	@Transient
	public String getId() {
		return username;
	}
}