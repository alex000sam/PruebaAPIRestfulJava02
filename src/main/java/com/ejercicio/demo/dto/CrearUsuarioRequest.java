package com.ejercicio.demo.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CrearUsuarioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Formato de correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Pattern(regexp = "${password.regexp}", message = "Formato de contraseña inválido")
    private String contrasena;

    private List<CrearTelefonoRequest> telefonos;

    public CrearUsuarioRequest(String nombre, String correo, String contrasena, List<CrearTelefonoRequest> telefonos) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefonos = telefonos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<CrearTelefonoRequest> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<CrearTelefonoRequest> telefonos) {
        this.telefonos = telefonos;
    }
}
