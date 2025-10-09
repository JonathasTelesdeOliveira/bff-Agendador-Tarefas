package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.controler;


import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.UsuarioService;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.EnderecoDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.TelefoneDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.in.UsuarioDTORequest;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.EnderecoDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.TelefoneDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.busines.dto.out.UsuarioDTOResponse;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro login e usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salver Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsusario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Login usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody UsuarioDTORequest usuarioDTO) {
        return usuarioService.login(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuário por email", description = "buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Salver Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Deletar Usuário por ID", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(@RequestBody UsuarioDTORequest dto,
                                                               @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Salver Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizarEnderecoId(@RequestBody EnderecoDTORequest dto,
                                                                   @RequestParam("id") Long id,
                                                                   @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Salver Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefoneId(@RequestBody TelefoneDTORequest dto,
                                                                  @RequestParam("id") Long id,
                                                                   @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosTelefone(id, dto, token));
    }

    @PostMapping("/enderecos")
    @Operation(summary = "Salver Enderço de Usuário",
            description = "Salva endereço do usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastroEndereco(token, dto));
    }

    @PostMapping("/telefones")
    @Operation(summary = "Salver telefone de Usuário",
            description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token, dto));
    }

}
