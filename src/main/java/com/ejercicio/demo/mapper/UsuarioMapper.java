package com.ejercicio.demo.mapper;

import com.ejercicio.demo.dto.CrearUsuarioRequest;
import com.ejercicio.demo.dto.CrearUsuarioResponse;
import com.ejercicio.demo.model.UsuarioModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    CrearUsuarioResponse toCrearUsuarioResponse(UsuarioModel usuario);
}
