package com.santosjhony.demo.park.api.web.controller;

import com.santosjhony.demo.park.api.entity.Cliente;
import com.santosjhony.demo.park.api.jwt.JwtUserDetails;
import com.santosjhony.demo.park.api.service.ClienteService;
import com.santosjhony.demo.park.api.service.UsuarioService;
import com.santosjhony.demo.park.api.web.dto.ClienteCreateDto;
import com.santosjhony.demo.park.api.web.dto.ClienteResponseDto;
import com.santosjhony.demo.park.api.web.dto.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails){
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }
}
