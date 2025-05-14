package com.ejercicio.demo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ListarUsuarioResponse {

    private UUID id;

    private String nombre;

    private String correo;

    private LocalDateTime creado;

    private LocalDateTime modificado;

    private LocalDateTime ultimoLogin;

    private boolean activo;

    public ListarUsuarioResponse(UUID id, String nombre, String correo, LocalDateTime creado, LocalDateTime modificado, LocalDateTime ultimoLogin, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.creado = creado;
        this.modificado = modificado;
        this.ultimoLogin = ultimoLogin;
        this.activo = activo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public LocalDateTime getModificado() {
        return modificado;
    }

    public void setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
