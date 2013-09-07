package de.professional_webworkx.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.constraints.Size;

@Constraint(validatedBy=ZipCodeValidator.class)
@Size(min=5, max=5)
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {

	String message() default "Postleitzahl ungültig!";
	
	Class[] groups() default {};
	
	Class[] payload() default {};
	
	public String regex() default "\\d{5}$";
}
