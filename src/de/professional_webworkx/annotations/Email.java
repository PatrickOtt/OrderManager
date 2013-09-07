package de.professional_webworkx.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy={EmailValidator.class})
@Documented
@Target({
	ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR,
	ElementType.PARAMETER, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

	String message() default "message.validation.invalidEmail";
	 
	 Class[] groups() default {};
	 
	 Class[] payload() default {};
	 
	 String regexp() default "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
}
