package com.jonathastelesoliviera.bffagendadortarefas.infraestruture.client;


import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.EnderecoDTORequest;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.LoginRequestDTO;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.TelefoneDTORequest;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.in.UsuarioDTORequest;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.EnderecoDTOResponse;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.TelefoneDTOResponse;
import com.jonathastelesoliviera.bffagendadortarefas.busines.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarPorEmail(@RequestParam("email") String email,
                                      @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    public TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                @RequestParam("id") Long id,
                                                @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/enderecos")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/telefones")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(value = "Authorization", required = false) String token);
}
