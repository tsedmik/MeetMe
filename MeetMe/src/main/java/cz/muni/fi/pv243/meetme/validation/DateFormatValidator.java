package cz.muni.fi.pv243.meetme.validation;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("datesFormatValidator")
public class DateFormatValidator implements Validator {
	
	private ResourceBundle msg = ResourceBundle.getBundle("cz.muni.fi.pv243.meetme.viewconfig.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		UIInput fromDateComponent = (UIInput) arg0.getViewRoot().findComponent("form:dates:fromDate");
		String fromDate = fromDateComponent.getValue().toString();
		
		UIInput fromTimeComponent = (UIInput) arg0.getViewRoot().findComponent("form:dates:fromTime");
		String fromTime = fromTimeComponent.getValue().toString();
		
		UIInput toDateComponent = (UIInput) arg0.getViewRoot().findComponent("form:dates:toDate");
		String toDate = toDateComponent.getValue().toString();
		
		UIInput toTimeComponent = (UIInput) arg0.getViewRoot().findComponent("form:dates:toTime");
		String toTime = toTimeComponent.getValue().toString();
		
		UIOutput labelComponent = (UIOutput) arg0.getViewRoot().findComponent("form:dates:label");
		String label = labelComponent.getValue().toString();
		
		// test zdali je neco na radku vyplnene
		if (!fromDate.equals("") || !fromTime.equals("") || !toDate.equals("") || !toTime.equals("")) {
			
			// pokud ano, tak musi byt vyplneny cely radek
			if (fromDate.equals("") || fromTime.equals("") || toDate.equals("") || toTime.equals("")) {
				Object[] messageArguments = {label};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.WholeRowFilled"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
			
			// validace na spravny format jednotlivych poli
			if (!fromDate.matches("^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)$")) {
				Object[] messageArguments = {label, msg.getString("eventedit.fromDate")};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.ValidDate"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
			if (!fromTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]?")) {
				Object[] messageArguments = {label, msg.getString("newevent.fromTime")};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.ValidTime"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
			if (!toDate.matches("^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)$")) {
				Object[] messageArguments = {label, msg.getString("newevent.toDate")};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.ValidDate"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
			if (!toTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]?")) {
				Object[] messageArguments = {label, msg.getString("newevent.toTime")};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.ValidTime"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
			
			// validace zdali je zacatek terminu driv nez konec terminu	
			String[] tempDate = fromDate.split("-");
			String[] tempTime = fromTime.split(":");
			GregorianCalendar from = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
			
			tempDate = toDate.split("-");
			tempTime = toTime.split(":");
			GregorianCalendar to = new GregorianCalendar(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]), Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
			
			if (!from.before(to)) {
				Object[] messageArguments = {label};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.BeforeAfter"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
			
			// validace zdali se nejedna o termin v minulosti
			Calendar now = GregorianCalendar.getInstance();
			if (from.before(now) || to.before(now)) {
				Object[] messageArguments = {label};
				MessageFormat formatter = new MessageFormat("");
				formatter.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
				formatter.applyPattern(msg.getString("DateFormatValidator.BeforeNow"));
				throw new ValidatorException(new FacesMessage(formatter.format(messageArguments)));
			}
	
		}
	}
}
