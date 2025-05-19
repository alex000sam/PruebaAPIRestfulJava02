package com.ejercicio.demo.controller;

import com.ejercicio.demo.dto.*;
import com.ejercicio.demo.mapper.UsuarioMapper;
import com.ejercicio.demo.model.UsuarioModel;
import com.ejercicio.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioMapper usuarioMapper;

    @GetMapping()
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<UsuarioModel> usuarios = usuarioService.listarTodosLosUsuarios();
            List<ListarUsuarioResponse> responses = usuarios.stream()
                    .map(usuario -> new ListarUsuarioResponse(
                            usuario.getId(),
                            usuario.getNombre(),
                            usuario.getCorreo(),
                            usuario.getCreado(),
                            usuario.getModificado(),
                            usuario.getUltimoLogin(),
                            usuario.isActivo()
                    ))
                    .toList();
            return ResponseEntity.ok(responses);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("mensaje", "Error al obtener los usuarios"));
        }
    }

    @PostMapping()
    public ResponseEntity<?> crearUsuario(@RequestBody CrearUsuarioRequest request) {
        UsuarioModel usuario = usuarioService.crearUsuario(request);
        CrearUsuarioResponse response = usuarioMapper.toCrearUsuarioResponse(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable UUID id, @RequestBody ModificarUsuarioRequest request) {
        UsuarioModel usuario = usuarioService.modificarUsuario(id, request);
        ModificarUsuarioResponse response = new ModificarUsuarioResponse(
                usuario.getId(),
                usuario.getModificado());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modificarParcialUsuario(@PathVariable UUID id, @RequestBody ModificarUsuarioRequest request) {
        UsuarioModel usuario = usuarioService.modificarParcialUsuario(id, request);
        ModificarUsuarioResponse response = new ModificarUsuarioResponse(
                usuario.getId(),
                usuario.getModificado());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable UUID id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("mensaje", "Usuario eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable UUID id) {
        UsuarioModel usuario = usuarioService.obtenerUsuario(id);
        ListarUsuarioResponse response = new ListarUsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getCreado(),
                usuario.getModificado(),
                usuario.getUltimoLogin(),
                usuario.isActivo());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
