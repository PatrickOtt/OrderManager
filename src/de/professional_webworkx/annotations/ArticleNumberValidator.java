package de.professional_webworkx.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArticleNumberValidator implements ConstraintValidator<ArticleNumber, String> {

	private String regEx;

	@Override
	public void initialize(ArticleNumber articleNumber) {
		// wir holen uns den Wert der als regex über die Annotation angegben wurde
		regEx = articleNumber.regex();
	}

	@Override
	public boolean isValid(String articleNumber, ConstraintValidatorContext context) {
		// pruefen wir 
		if(articleNumber.matches(regEx))
			return true;
		else
			return false;
	}
	
	
}
