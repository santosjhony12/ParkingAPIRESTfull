package com.santosjhony.demo.park.api.repository;

import com.santosjhony.demo.park.api.entity.Cliente;
import com.santosjhony.demo.park.api.repository.projection.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("select c from Cliente c")
    Page<ClienteProjection> findAllPageable(Pageable pageable);

    Cliente findByUsuarioId(Long id);
}
