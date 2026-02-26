package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client;


import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.EnderecoDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.LoginRequestDTO;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TelefoneDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.UsuarioDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.EnderecoDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TelefoneDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.UsuarioDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
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
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                @RequestParam("id") Long id,
                                                @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/enderecos")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/telefones")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscaDadosDoCep(@PathVariable("cep") String cep);



}
