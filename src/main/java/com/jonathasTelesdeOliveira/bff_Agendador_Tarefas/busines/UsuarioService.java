package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines;


import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.EnderecoDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TelefoneDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.UsuarioDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.EnderecoDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TelefoneDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.UsuarioDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvarUsusario(UsuarioDTORequest usuarioDTO) {
        return client.salvarUsuario(usuarioDTO);
    }

    public String login(UsuarioDTORequest usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarPorEmail(String email, String token) {
        return client.buscarPorEmail(email, token);
    }
    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizarUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizarDadosEndereco(Long id, EnderecoDTORequest dto, String token) {
       return client.atualizarEnderecoId(dto, id, token);
    }

    public TelefoneDTOResponse atualizarDadosTelefone(Long id, TelefoneDTORequest dto, String token) {
        return client.atualizarTelefoneId(dto, id, token);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest dto) {
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto) {
        return client.cadastraTelefone(dto, token);
    }

}
