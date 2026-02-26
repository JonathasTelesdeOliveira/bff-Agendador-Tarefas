package com.jonathastelesoliviera.bffagendadortarefas.busines;


import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.EnderecoDTORequest;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.LoginRequestDTO;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.TelefoneDTORequest;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.UsuarioDTORequest;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.EnderecoDTOResponse;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.TelefoneDTOResponse;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.UsuarioDTOResponse;
import com.jonathastelesoliviera.bffagendadortarefas.infraestruture.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvarUsusario(UsuarioDTORequest usuarioDTO) {

        return client.salvarUsuario(usuarioDTO);
    }

    public String loginUsusario(LoginRequestDTO dto){

        return client.login(dto);
    }

    public UsuarioDTOResponse buscarPorEmail(String email, String token) {
        return client.buscarPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizarDadosEndereco(Long id, EnderecoDTORequest dto, String token) {
       return client.atualizaEndereco(dto, id, token);
    }

    public TelefoneDTOResponse atualizarDadosTelefone(Long id, TelefoneDTORequest dto, String token) {
        return client.atualizaTelefone(dto, id, token);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest dto) {
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto) {
        return client.cadastraTelefone(dto, token);
    }

}
