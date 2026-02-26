package com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TelefoneDTORequest {

    private String numero;
    private String ddd;
}
