package com.santosjhony.demo.park.api.web.controller;

import com.santosjhony.demo.park.api.jwt.JwtToken;
import com.santosjhony.demo.park.api.jwt.JwtUserDetailsService;
import com.santosjhony.demo.park.api.web.dto.UsuarioLoginDto;
import com.santosjhony.demo.park.api.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AutenticacaoController {
    private final JwtUserDetailsService detailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request) {
        log.info("Processo de autenticação pelo login", dto.getUsername());
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailService.getTokenAuthenticated(dto.getUsername());
            return ResponseEntity
                    .ok()
                    .body(token);
        }catch (AuthenticationException ex){
            log.warn("Bad Credentionals from username {}", dto.getUsername());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais invalidas."));
    }
}
