package cz.muni.fi.pv243.meetme.viewconfig;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.rewrite.UrlMapping;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.jboss.seam.security.annotations.LoggedIn;

@ViewConfig
public interface Pages {
	static enum Pages1 {
		
		@ViewPattern("/participate.xhtml")
		@UrlMapping(pattern="/event/#{id}/")
        PARTICIPATE,
        
        @ViewPattern("/participateAuth.xhtml")
		@UrlMapping(pattern="/auth/event/#{id}/")
		@LoginView("/login.xhtml")
		@LoggedIn
		@RestrictAtPhase({PhaseIdType.RESTORE_VIEW, PhaseIdType.INVOKE_APPLICATION})
        PARTICIPATE_AUTH
	}

}
