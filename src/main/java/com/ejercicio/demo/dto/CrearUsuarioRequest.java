package com.ejercicio.demo.dto;

import java.util.List;

public class CrearUsuarioRequest {

    private String nombre;

    private String correo;

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
