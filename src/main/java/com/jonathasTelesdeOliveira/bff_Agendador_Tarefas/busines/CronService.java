package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines;

import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.Enums.StatusNotificacaoEnum;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.LoginRequestDTO;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TarefaDTOResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefaProximaHora() {
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de Tarefas");

        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefaDTOResponse> listaTarefas = tarefaService.buscarTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);
        log.info("Tarefas encontradas!" + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario " + tarefa.getEmailUsuario());

            tarefaService.atualizaStatusNotificacao(StatusNotificacaoEnum.Notificado, tarefa.getId(), token);
        });
        log.info("Finalizado a busca de notificação de tarefas");
    }

    public String login(LoginRequestDTO dto) {
        return usuarioService.loginUsusario(dto);
    }

    public LoginRequestDTO converterParaRequestDTO() {
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
