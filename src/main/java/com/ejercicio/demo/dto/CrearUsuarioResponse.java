package com.ejercicio.demo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CrearUsuarioResponse {

    private UUID id;

    private LocalDateTime creado;

    private LocalDateTime modificado;

    private LocalDateTime ultimoLogin;

    private String token;

    private boolean activo;

    public CrearUsuarioResponse(UUID id, LocalDateTime creado, LocalDateTime modificado, LocalDateTime ultimoLogin, String token, boolean activo) {
        this.id = id;
        this.creado = creado;
        this.modificado = modificado;
        this.ultimoLogin = ultimoLogin;
        this.token = token;
        this.activo = activo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
