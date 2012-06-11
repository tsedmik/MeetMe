package cz.muni.fi.pv243.meetme.security;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

import cz.muni.fi.pv243.meetme.model.Person;

public class PersonAuthenticator extends BaseAuthenticator {

	@Inject
	Credentials credentials;

	@PersistenceContext
	EntityManager em;

	@Override
	public void authenticate() {
		try {
			
			Person person = (Person) em
					.createQuery(
							"select m from Person m where m.username = :username and m.password = :password")
					.setParameter("username", credentials.getUsername())
					.setParameter("password", HashPassword.byteArrayToHexString(HashPassword.computeHash(((PasswordCredential) credentials.getCredential()).getValue())))
					.getSingleResult();

			setStatus(AuthenticationStatus.SUCCESS);
			setUser(person);
		} catch (NoResultException x) {
			//TODO logovani neuspesneho pokusu o prihlaseni
			setStatus(AuthenticationStatus.FAILURE);
		} catch (Exception e) {
			//TODO logovani chyby prevodu hesla
			setStatus(AuthenticationStatus.FAILURE);
		}

	}

}
