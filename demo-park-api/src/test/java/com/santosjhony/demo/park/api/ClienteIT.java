package com.santosjhony.demo.park.api;

import com.santosjhony.demo.park.api.web.dto.ClienteCreateDto;
import com.santosjhony.demo.park.api.web.dto.ClienteResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/clientes/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/clientes-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClienteIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void criarCliente_ComDadosValidos_RetornarClienteComStatus201(){
        ClienteResponseDto responseBody = testClient.post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "toby@gmail.com", "123456"))
                .bodyValue(new ClienteCreateDto("Toby Ferreira", "58567414040"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ClienteResponseDto.class)
                .returnResult()
                .getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("Toby Ferreira");
        org.assertj.core.api.Assertions.assertThat(responseBody.getCpf()).isEqualTo("58567414040");


    }
}
