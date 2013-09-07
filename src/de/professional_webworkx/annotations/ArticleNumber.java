package de.professional_webworkx.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

// wer validiert die regex für uns
@Constraint(validatedBy=ArticleNumberValidator.class)
// wo können wir die Annotation überall ranschreiben
@Target(value={
		ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD,
		ElementType.METHOD, ElementType.PARAMETER
})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleNumber {

	// hier die Methode, wie man es auch einem herkömmlichen Interface kennt
	String message() default "de.professional_webworkx.annotation.invalidArticleNumber";
	
	Class<?>[] groups() default {};
	
	Class<?>[] payload() default {};
	
	String regex() default "^[\\w]-[\\d]{1,10}$";
}
