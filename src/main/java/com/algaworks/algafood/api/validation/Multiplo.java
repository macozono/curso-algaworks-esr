package com.algaworks.algafood.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class})
public @interface Multiplo {

	String message() default "múltiplo inválido";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	int numero();
}
