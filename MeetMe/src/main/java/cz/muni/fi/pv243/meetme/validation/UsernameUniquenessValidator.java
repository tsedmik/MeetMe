package cz.muni.fi.pv243.meetme.validation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.faces.validation.InputField;

import cz.muni.fi.pv243.meetme.model.Person;

@FacesValidator("usernameUniquenessValidator")
public class UsernameUniquenessValidator implements Validator {
	
	@Inject
	@InputField
	private String username;
	
	@PersistenceContext
	EntityManager em;

	private ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		if (!em.createQuery("select b from Person b where b.username = ?1", Person.class).setParameter(1, username).getResultList().isEmpty())
			throw new ValidatorException(new FacesMessage(msg.getString("validation.uniqueUsername")));
		
	}
	


}
