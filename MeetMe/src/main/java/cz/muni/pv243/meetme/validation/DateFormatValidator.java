package cz.muni.pv243.meetme.validation;


import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import cz.muni.fi.pv243.meetme.action.DatesItem;

@FacesValidator("datesFormatValidator")
public class DateFormatValidator implements Validator {
	
	ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		//TODO validace terminu na vyplnenost a korektnost
		UIData dataTable = (UIData)arg0.getViewRoot().findComponent("form:dates");
		DatesItem currentItem = (DatesItem) dataTable.getRowData();

		
		if (currentItem == new DatesItem()) {
			throw new ValidatorException(new FacesMessage(msg.getString("validation.passwordMatch")));
		}
	}
	
	

}
