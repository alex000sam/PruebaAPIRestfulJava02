package com.ejercicio.demo.dto;

import java.util.List;
import java.util.UUID;

public class ModificarUsuarioRequest {

    private String nombre;

    private String correo;

    private String contrasena;

    private boolean activo;

    private List<CrearTelefonoRequest> telefonos;

    public ModificarUsuarioRequest(String nombre, String correo, String contrasena, boolean activo, List<CrearTelefonoRequest> telefonos) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.activo = activo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<CrearTelefonoRequest> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<CrearTelefonoRequest> telefonos) {
        this.telefonos = telefonos;
    }
}
