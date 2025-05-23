package com.ejercicio.demo.service;

import com.ejercicio.demo.dto.CrearTelefonoRequest;
import com.ejercicio.demo.dto.CrearUsuarioRequest;
import com.ejercicio.demo.dto.ModificarUsuarioRequest;
import com.ejercicio.demo.exception.UsuarioNoEncontradoException;
import com.ejercicio.demo.model.TelefonoModel;
import com.ejercicio.demo.model.UsuarioModel;
import com.ejercicio.demo.repository.UsuarioRepository;
import com.ejercicio.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${password.regexp}")
    public String passwordRegex;

    @Value("${correo.regexp}")
    public String correoRegex;

    public List<UsuarioModel> listarTodosLosUsuarios() {
        return (List<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel crearUsuario(CrearUsuarioRequest request) {

        //validar si existe usuario por correo
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        //Validar formato de correo
        if (!Pattern.matches(correoRegex, request.getCorreo())) {
            throw new RuntimeException("Formato de correo inválido");
        }

        //validar formato de contraseña
        if (!Pattern.matches(passwordRegex, request.getContrasena())) {
            throw new RuntimeException("Formato de contraseña inválido");
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(request.getContrasena());
        usuario.setNombre(request.getNombre());
        usuario.setCreado(LocalDateTime.now());
        usuario.setModificado(LocalDateTime.now());
        usuario.setUltimoLogin(LocalDateTime.now());
        usuario.setActivo(true);
        usuario.setToken(jwtUtil.generarToken(request.getCorreo()));

        List<TelefonoModel> telefonos = request.getTelefonos().stream()
            .map(tr -> {
                TelefonoModel telefono = new TelefonoModel();
                telefono.setNumero(tr.getNumero());
                telefono.setCodigoCiudad(tr.getCodigoCiudad());
                telefono.setCodigoPais(tr.getCodigoPais());
                telefono.setUsuario(usuario);
                return telefono;
            })
            .collect(Collectors.toList());

        usuario.setTelefonos(telefonos);

        return usuarioRepository.save(usuario);
    }

    public UsuarioModel modificarUsuario(UUID id, ModificarUsuarioRequest request) {

        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        //Validar formato de correo
        if (!Pattern.matches(correoRegex, request.getCorreo())) {
            throw new RuntimeException("Formato de correo inválido");
        }

        //Validar formato de contraseña
        if (!Pattern.matches(passwordRegex, request.getContrasena())) {
            throw new RuntimeException("Formato de contraseña inválido");
        }

        //Validar si el correo está registrado por otro usuario
        Optional<UsuarioModel> existente = usuarioRepository.findByCorreo(request.getCorreo());
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            throw new RuntimeException("El correo ya está registrado por otro usuario");
        }

        //Actualizar campos
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(request.getContrasena());
        usuario.setNombre(request.getNombre());
        usuario.setActivo(request.getActivo());
        usuario.setModificado(LocalDateTime.now());

        List<TelefonoModel> telefonos = request.getTelefonos().stream()
            .map(tr -> {
                TelefonoModel telefono = new TelefonoModel();
                telefono.setNumero(tr.getNumero());
                telefono.setCodigoCiudad(tr.getCodigoCiudad());
                telefono.setCodigoPais(tr.getCodigoPais());
                telefono.setUsuario(usuario);
                return telefono;
            })
            .collect(Collectors.toList());

        usuario.setTelefonos(telefonos);

        return usuarioRepository.save(usuario);
    }

    public UsuarioModel modificarParcialUsuario(UUID id, ModificarUsuarioRequest request) {

        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        //Validar formato de correo
        if (request.getCorreo() != null && !request.getCorreo().isBlank()) {

            if (!Pattern.matches(correoRegex, request.getCorreo())) {
                throw new RuntimeException("Formato de correo inválido");
            }

            //Validar si el correo está registrado por otro usuario
            Optional<UsuarioModel> existente = usuarioRepository.findByCorreo(request.getCorreo());
            if (existente.isPresent() && !existente.get().getId().equals(id)) {
                throw new RuntimeException("El correo ya está registrado por otro usuario");
            }

            usuario.setCorreo(request.getCorreo());
        }

        if (request.getNombre() != null && !request.getNombre().isBlank()) {
            usuario.setNombre(request.getNombre());
        }

        if (request.getContrasena() != null && !request.getContrasena().isBlank()) {
            //Validar formato de contraseña
            if (!Pattern.matches(passwordRegex, request.getContrasena())) {
                throw new RuntimeException("Formato de contraseña inválido");
            }
            usuario.setContrasena(request.getContrasena());
        }

        if (request.getTelefonos() != null) {
            List<TelefonoModel> telefonos = request.getTelefonos().stream()
                .map(tr -> {
                    TelefonoModel telefono = new TelefonoModel();
                    telefono.setNumero(tr.getNumero());
                    telefono.setCodigoCiudad(tr.getCodigoCiudad());
                    telefono.setCodigoPais(tr.getCodigoPais());
                    telefono.setUsuario(usuario);
                    return telefono;
                })
                .collect(Collectors.toList());
            usuario.setTelefonos(telefonos);
        }

        usuario.setModificado(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioModel obtenerUsuario(UUID id) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        return usuario;
    }
}
