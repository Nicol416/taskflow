package com.example.taskflow.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private Estado estado;

    @NotNull(message = "La prioridad es obligatoria")
    private Prioridad prioridad;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    private LocalDate fechaCreacion;

    @NotNull(message = "La fecha límite es obligatoria")
    private LocalDate fechaLimite;

    public Long getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }
}