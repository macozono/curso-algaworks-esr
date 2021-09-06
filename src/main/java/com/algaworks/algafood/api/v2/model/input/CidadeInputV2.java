package com.algaworks.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInputV2 {

    @NotBlank
    @ApiModelProperty(example = "São Paulo")
    private String nomeCidade;
    
    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Long idCidade;
}