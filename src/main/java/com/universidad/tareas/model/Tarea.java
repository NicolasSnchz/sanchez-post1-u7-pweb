package com.universidad.tareas.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class Tarea {

    private Long id;

    @NotBlank(message = "El t\u00edtulo es obligatorio")
    @Size(min = 3, max = 100, message = "El t\u00edtulo debe tener entre 3 y 100 caracteres")
    private String titulo;

    @Size(max = 500, message = "La descripci\u00f3n no puede superar 500 caracteres")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private Prioridad prioridad;

    @NotNull(message = "La fecha l\u00edmite es obligatoria")
    @FutureOrPresent(message = "La fecha l\u00edmite no puede ser anterior a hoy")
    private LocalDate fechaLimite;

    private boolean completada;

    // Constructor sin argumentos (requerido por Spring para el binding de formularios y JSON)
    public Tarea() {}

    public Tarea(Long id, String titulo, String descripcion, Prioridad prioridad,
                 LocalDate fechaLimite, boolean completada) {
        this.id = id;
        setTitulo(titulo);
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
        this.completada = completada;
    }

    // Getters y Setters (necesarios para Thymeleaf y para la serializacion JSON con Jackson)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    // Un titulo vacio o solo con espacios se guarda como null: asi solo falla
    // @NotBlank ("obligatorio") y no tambien @Size, que considera valido el null
    public void setTitulo(String titulo) {
        this.titulo = (titulo == null || titulo.isBlank()) ? null : titulo.trim();
    }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
}
