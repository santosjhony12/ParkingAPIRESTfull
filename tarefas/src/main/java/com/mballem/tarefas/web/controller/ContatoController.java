package com.mballem.tarefas.web.controller;

import com.mballem.internal.entity.Contato;
import com.mballem.internal.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tarefas/contatos")
@RequiredArgsConstructor
public class ContatoController {
    private final ContatoService contatoService;

    // EXERCICIO 1
    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato) {
        Contato contact = contatoService.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    // EXERCICIO 2
    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Contato contact = contatoService.getById(id);
        return ResponseEntity.ok().body(contact);
    }

    // EXERCICIO 3
    @GetMapping("/name/{name}")
    public ResponseEntity<Contato> getContatoByNome(@PathVariable String name) {
        Contato contact = contatoService.getContatoByNome(name);
        return ResponseEntity.ok(contact);
    }

    // EXERCICIO 4
    @GetMapping("/quant")
    public ResponseEntity<Integer> getQuantidadeDeContatos() {
        int quantidade = contatoService.getAll();
        return ResponseEntity.ok(quantidade);
    }

    // EXERCICIO 5
    @GetMapping("/dataNascimento/{data}")
    public ResponseEntity<List<Contato>> getContatosByDataNascimento(@PathVariable LocalDate data) {
        List<Contato> contatos = contatoService.getByDataNascimento(data);
        return ResponseEntity.ok().body(contatos);
    }

    // EXERCICIO 6
    @PatchMapping("/{id}")
    public ResponseEntity<Contato> updateContatoById(@PathVariable Long id, @RequestBody Contato contato) {
        Contato cont = contatoService.update(id, contato);
        return ResponseEntity.ok(cont);
    }

    // EXERCICIO 7
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String string = contatoService.delete(id);
        return ResponseEntity.ok(string);
    }
}
