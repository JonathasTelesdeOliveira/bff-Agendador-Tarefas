package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequestDTO {
    private String email;
    private String senha;
}
