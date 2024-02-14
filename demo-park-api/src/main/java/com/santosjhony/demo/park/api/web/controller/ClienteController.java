package com.santosjhony.demo.park.api.web.controller;

import com.santosjhony.demo.park.api.entity.Cliente;
import com.santosjhony.demo.park.api.jwt.JwtUserDetails;
import com.santosjhony.demo.park.api.service.ClienteService;
import com.santosjhony.demo.park.api.service.UsuarioService;
import com.santosjhony.demo.park.api.web.dto.ClienteCreateDto;
import com.santosjhony.demo.park.api.web.dto.ClienteResponseDto;
import com.santosjhony.demo.park.api.web.dto.UsuarioResponseDto;
import com.santosjhony.demo.park.api.web.dto.mapper.ClienteMapper;
import com.santosjhony.demo.park.api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @Operation(
            summary = "Criar um novo cliente.",
            description = "Recurso para criar um novo cliente vinculado a um cadastrado."+
                        "Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENTE'",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Cliente CPF já cadastrado no sistena",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido no perfil de ADMIN.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails){
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteResponseDto> getById(@PathVariable Long id){
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok().body(ClienteMapper.toDto(cliente));
    }
}
