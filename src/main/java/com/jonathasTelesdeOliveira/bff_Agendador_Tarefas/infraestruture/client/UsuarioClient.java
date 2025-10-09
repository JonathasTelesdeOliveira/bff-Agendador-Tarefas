package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client;


import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.EnderecoDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TelefoneDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.UsuarioDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.EnderecoDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TelefoneDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.UsuarioDTOResponse;
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
    String login(@RequestBody UsuarioDTORequest usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizarUsuario(@RequestBody UsuarioDTORequest dto,
                                        @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarEnderecoId(@RequestBody EnderecoDTORequest dto,
                                            @RequestParam("id") Long id,
                                            @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    public TelefoneDTOResponse atualizarTelefoneId(@RequestBody TelefoneDTORequest dto,
                                                   @RequestParam("id") Long id,
                                                   @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/enderecos")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/telefones")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(value = "Authorization", required = false) String token);
}
