package com.santosjhony.demo.park.api.repository;

import com.santosjhony.demo.park.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
