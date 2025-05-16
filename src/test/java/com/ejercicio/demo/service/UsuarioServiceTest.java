package com.ejercicio.demo.service;

import com.ejercicio.demo.dto.CrearTelefonoRequest;
import com.ejercicio.demo.dto.CrearUsuarioRequest;
import com.ejercicio.demo.model.UsuarioModel;
import com.ejercicio.demo.repository.UsuarioRepository;
import com.ejercicio.demo.security.JwtUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        //Expresión regular para la contraseña
        usuarioService.passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        usuarioService.correoRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    }

    @Test
    public void testCrearUsuario_Ok() {

        CrearUsuarioRequest request = new CrearUsuarioRequest(
                "Alexander",
                "alexander@correo.com",
                "abc123",
                List.of(new CrearTelefonoRequest("111111111", "84", "51")));

        Mockito.when(usuarioRepository.existsByCorreo("alexander@correo.com")).thenReturn(false);
        Mockito.when(jwtUtil.generarToken("alexander@correo.com")).thenReturn("token-fake");
        Mockito.when(usuarioRepository.save(Mockito.any(UsuarioModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UsuarioModel resultado = usuarioService.crearUsuario(request);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Alexander", resultado.getNombre());
        Assertions.assertEquals("alexander@correo.com", resultado.getCorreo());
        Assertions.assertEquals("token-fake", resultado.getToken());
        Assertions.assertTrue(resultado.isActivo());
        Mockito.verify(usuarioRepository).save(Mockito.any(UsuarioModel.class));
    }

    @Test
    void testCrearUsuario_CorreoDuplicado() {

        CrearUsuarioRequest request = new CrearUsuarioRequest(
                "Alexander",
                "alexander@correo.com",
                "abc123",
                null);

        Mockito.when(usuarioRepository.existsByCorreo(request.getCorreo())).thenReturn(true);
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> usuarioService.crearUsuario(request));
        Assertions.assertEquals("El correo ya está registrado", ex.getMessage());
    }

    @Test
    void testCrearUsuario_FormatoCorreoInvalido() {

        CrearUsuarioRequest request = new CrearUsuarioRequest(
                "Alexander",
                "correo-sin-arroba",
                "abc123",
                null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> usuarioService.crearUsuario(request));
        Assertions.assertEquals("Formato de correo inválido", ex.getMessage());
    }

    @Test
    void testCrearUsuario_ContraseñaInvalida() {

        CrearUsuarioRequest request = new CrearUsuarioRequest(
                "Alexander",
                "alexander@correo.com",
                "soloLetras",
                null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> usuarioService.crearUsuario(request));
        Assertions.assertEquals("Formato de contraseña inválido", ex.getMessage());
    }
}
