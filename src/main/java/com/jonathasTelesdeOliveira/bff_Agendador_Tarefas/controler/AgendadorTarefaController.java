package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.controler;


import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.AgendadorTarefaService;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TarefaDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TarefaDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.Enums.StatusNotificacaoEnum;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadasstra tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class AgendadorTarefaController {

    private final AgendadorTarefaService agendadorTarefaService;

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest dto,
                                                          @RequestHeader(value = "Authorization", required = false) String token) {
       return ResponseEntity.ok(agendadorTarefaService.gravandoTarefaDTO(token, dto));
    }


    @GetMapping("/evento")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(
                agendadorTarefaService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usuário",
            description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefaEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        List<TarefaDTOResponse> tarefas = agendadorTarefaService.buscarTarefaPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id", description = "Deleta tarefas cadastrads por ID")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletarTarefaId(
            @RequestParam("id") String id,
            @RequestHeader(value = "Authorization", required = false) String token) {
        agendadorTarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTOResponse> atualizarStatusTarefa(
                                                        @RequestParam("status") StatusNotificacaoEnum status,
                                                        @RequestParam("id") String id,
                                                        @RequestHeader(value = "Authorization", required = false) String token) {
            return ResponseEntity.ok(agendadorTarefaService.alteraStatusTarefa(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alterads")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTOResponse> updateTarefa(
                                                @RequestBody TarefaDTORequest dto,
                                                @RequestParam("id") String id,
                                                @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(
                agendadorTarefaService.updateDeTarefas(dto, id, token));
    }
}
