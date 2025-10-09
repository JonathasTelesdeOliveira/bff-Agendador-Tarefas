package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines;


import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TarefaDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TarefaDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.Enums.StatusNotificacaoEnum;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client.AgendadorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendadorTarefaService {


    private final AgendadorClient tarefasClient;

    public TarefaDTOResponse gravandoTarefaDTO(String token, TarefaDTORequest dto) {
        return tarefasClient.gravarTarefa(dto, token);
    }

    public List<TarefaDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefasClient.buscarTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaDTOResponse> buscarTarefaPorEmail(String token) {
        return tarefasClient.buscarTarefaEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaId(id, token);
    }

    public TarefaDTOResponse alteraStatusTarefa(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.atualizarStatusTarefa(status, id, token);
    }

    public TarefaDTOResponse updateDeTarefas(TarefaDTORequest dto, String id, String token) {
        return tarefasClient.updateTarefa(dto, id, token);
    }
}
