package com.algaworks.algafood.api.validation;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

public class ValorZeroInlcuiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraint) {
		this.valorField = constraint.valorField();
		this.descricaoField = constraint.descricaoField();
		this.descricaoObrigatoria = constraint.descricaoObrigatoria();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valido = true;
		
		try {
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(value.getClass(), valorField)
					.getReadMethod().invoke(value, new Object[] {});
			
			String descricao = (String) BeanUtils.getPropertyDescriptor(value.getClass(), descricaoField)
					.getReadMethod().invoke(value, new Object[] {});
			
			if (valor != null && BigDecimal.ZERO.compareTo(valor)  == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}
			
		} catch (BeansException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ValidationException("Ocorreu um erro na execução do validation " + this.getClass().getName(), e);
		}
		
		return valido;
	}
}
