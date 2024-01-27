package com.santosjhony.demo.park.api.service;

import com.santosjhony.demo.park.api.entity.Usuario;
import com.santosjhony.demo.park.api.exception.EntityNotFoundException;
import com.santosjhony.demo.park.api.exception.PasswordInvalidException;
import com.santosjhony.demo.park.api.exception.UsernameUniqueViolationException;
import com.santosjhony.demo.park.api.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //faz a injeção de dep
@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        }catch(org.springframework.dao.DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado.", usuario.getUsername()));
        }
    }
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(String.format("Usuário id: %s não encontrado", id))
        );
    }
    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha){
        if(!novaSenha.equals(confirmaSenha)){
            throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
        }
        Usuario user = buscarPorId(id);
        if(!user.getPassword().equals(senhaAtual)){
            throw new PasswordInvalidException("Sua senha não confere.");
        }
        user.setPassword(novaSenha);
        return user;
    }
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário com username com ID %s não encontrado.", username))
        );
    }

    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
