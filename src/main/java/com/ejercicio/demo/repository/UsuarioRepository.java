package com.ejercicio.demo.repository;

import com.ejercicio.demo.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, UUID> {

    boolean existsByCorreo(String correo);

    Optional<UsuarioModel> findByCorreo(String correo);
}
