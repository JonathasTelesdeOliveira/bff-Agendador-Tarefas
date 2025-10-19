package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client;

import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOResponse dto);
}
