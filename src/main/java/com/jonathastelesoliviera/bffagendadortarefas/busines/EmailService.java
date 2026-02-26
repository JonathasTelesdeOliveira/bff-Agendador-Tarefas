package com.jonathastelesoliviera.bffagendadortarefas.busines;


import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.TarefaDTOResponse;
import com.jonathastelesoliviera.bffagendadortarefas.infraestruture.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefaDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }
}
