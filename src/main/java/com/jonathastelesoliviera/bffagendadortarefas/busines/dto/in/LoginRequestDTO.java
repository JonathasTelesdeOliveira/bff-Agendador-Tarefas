package com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in;

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
