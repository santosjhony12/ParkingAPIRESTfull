package com.santosjhony.demo.park.api.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class ClienteCreateDto {
    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;
}
