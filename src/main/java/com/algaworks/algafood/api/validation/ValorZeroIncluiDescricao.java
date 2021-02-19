package com.algaworks.algafood.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValorZeroInlcuiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao {

	String message() default "descrição obrigatória inválida";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String valorField();
	String descricaoField();
	String descricaoObrigatoria();
}
