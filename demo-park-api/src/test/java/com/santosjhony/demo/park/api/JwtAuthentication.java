package com.santosjhony.demo.park.api;

import com.santosjhony.demo.park.api.jwt.JwtToken;
import com.santosjhony.demo.park.api.web.dto.UsuarioLoginDto;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

public class JwtAuthentication {
    public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String username, String password){
        String token = client
                .post()
                .uri("/api/v1/auth")
                .bodyValue(new UsuarioLoginDto(username, password ))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult()
                .getResponseBody()
                .getToken();
        return hearders -> hearders.add(HttpHeaders.AUTHORIZATION, "Bearer "+token);
    }
}
