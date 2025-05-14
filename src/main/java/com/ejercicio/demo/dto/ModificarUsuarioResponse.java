package com.ejercicio.demo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ModificarUsuarioResponse {

    private UUID id;

    private LocalDateTime modificado;

    public ModificarUsuarioResponse(UUID id, LocalDateTime modificado) {
        this.id = id;
        this.modificado = modificado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getModificado() {
        return modificado;
    }

    public void setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
    }
}
