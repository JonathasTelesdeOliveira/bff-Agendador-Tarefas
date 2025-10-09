package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client;

import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TarefaDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TarefaDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.Enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-Tarefas", url = "${agendador-Tarefas.url}")
public interface AgendadorClient {

    @PostMapping
    TarefaDTOResponse gravarTarefa(
                            @RequestBody TarefaDTORequest dto,
                            @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/evento")
    List<TarefaDTOResponse> buscarTarefasPorPeriodo(
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                    @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping
    List<TarefaDTOResponse> buscarTarefaEmail(@RequestHeader(value = "Authorization", required = false) String token);

    @DeleteMapping
    Void deletarTarefaId(
                        @RequestParam("id") String id,
                        @RequestHeader(value = "Authorization", required = false) String token);

    @PatchMapping
    TarefaDTOResponse atualizarStatusTarefa(
                                    @RequestParam("status") StatusNotificacaoEnum status,
                                    @RequestParam("id") String id,
                                    @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    TarefaDTOResponse updateTarefa(
                        @RequestBody TarefaDTORequest dto,
                        @RequestParam("id") String id,
                        @RequestHeader(value = "Authorization", required = false) String token);


}
