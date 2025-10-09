package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TelefoneDTORequest {
    private long id;
    private String numero;
    private String ddd;
}
