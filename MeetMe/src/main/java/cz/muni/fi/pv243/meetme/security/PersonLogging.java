package cz.muni.fi.pv243.meetme.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.seam.security.events.LoggedInEvent;
import org.jboss.solder.logging.Logger;

/**
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com 
 * @since 2012-05-14
 *
 */
@ApplicationScoped
public class PersonLogging {
	
	public void personLoggedIn(@Observes LoggedInEvent event, Logger log) {
		log.info("User " + event.getUser().getId() + " logged in.");
    }

}
