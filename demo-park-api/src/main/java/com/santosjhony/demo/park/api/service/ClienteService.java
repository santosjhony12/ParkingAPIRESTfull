package com.santosjhony.demo.park.api.service;

import com.santosjhony.demo.park.api.entity.Cliente;
import com.santosjhony.demo.park.api.exception.CpfUniqueViolationException;
import com.santosjhony.demo.park.api.exception.EntityNotFoundException;
import com.santosjhony.demo.park.api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){
        try{
            return clienteRepository.save(cliente);
        }catch (DataIntegrityViolationException ex){
            throw new CpfUniqueViolationException(String.format("CPF %s não pode ser cadastrado, já existe no sistema.", cliente.getCpf()));
        }
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente id %s não encontrado no sistema", id))
        );
    }
}
