package de.professional_webworkx.annotations;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

	@Override
	public void initialize(ZipCode zip) {
		
	}

	@Override
	public boolean isValid(String zip, ConstraintValidatorContext context) {
		if(Pattern.matches("\\d{5}", zip))
			return true;
		else
			return false;
	}

	
}
