package com.algaworks.algafood.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@PositiveOrZero
public @interface TaxaFrete {

	@OverridesAttribute(constraint = PositiveOrZero.class, name="message")
	String message() default "{TaxaFrete.invalida}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
