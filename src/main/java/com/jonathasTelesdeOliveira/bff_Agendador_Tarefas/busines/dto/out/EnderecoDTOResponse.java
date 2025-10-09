package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class EnderecoDTOResponse {
    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
