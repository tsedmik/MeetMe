package cz.muni.fi.pv243.meetme.security;

import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.events.LoggedInEvent;
import org.jboss.seam.security.events.LoginFailedEvent;
import org.jboss.solder.logging.Logger;



/**
 * 
 * @author Tomáš Sedmík, tomas.sedmik@gmail.com 
 * @since 2012-05-14
 *
 */
@ApplicationScoped
public class PersonLogging {
	
	ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages_cs");
	
	public void personLoggedIn(@Observes LoggedInEvent event, Logger log) {
		log.info("User " + event.getUser().getId() + " logged in.");
    }
	
	public void personLoginFailed(@Observes LoginFailedEvent event, Messages messages) {
		messages.error(msg.getString("login.loginFailed"));
	}
}