package de.professional_webworkx.annotations;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public void initialize(Email value) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		try {
			new InternetAddress(value).validate();
		} catch(AddressException ex) {
			return false;
		}
		return true;
	}
}
