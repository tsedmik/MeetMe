package cz.muni.fi.pv243.meetme.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import cz.muni.fi.pv243.meetme.annotation.Current;
import cz.muni.fi.pv243.meetme.model.Event;

/**
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com
 * @since 2012-05-14
 *
 */
public class PersonAuthorization {
	
	@Secures
	@IsEventOwnerOf
	public boolean isEventOwnerOf(Identity identity, @Current Event event) {
		
		if (event == null) {
			return false;
		}
		
		if (event.getOwner().getUsername().equals("unknown")) {
			return true;
		}
		
		if (!identity.isLoggedIn()) {
			return false;
		}
	            
	    return identity.getUser().getId().equals(event.getOwner().getId());
	}

}
