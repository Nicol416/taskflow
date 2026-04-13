package com.example.taskflow.Repository;

import com.example.taskflow.Model.Estado;
import com.example.taskflow.Model.Prioridad;
import com.example.taskflow.Model.Tarea;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TareaRepository {

    private List<Tarea> tareas = new ArrayList<>();
    private Long contadorId = 3L;

    public TareaRepository() {
        tareas.add(new Tarea(1L, "Revisar sistema", "Ver errores", Estado.PENDIENTE,
                Prioridad.MEDIA, "Nicol", LocalDate.now(), LocalDate.now().plusDays(5)));

        tareas.add(new Tarea(2L, "Actualizar BD", "Migrar datos", Estado.EN_PROGRESO,
                Prioridad.ALTA, "Valentina", LocalDate.now(), LocalDate.now().plusDays(3)));
    }

    public List<Tarea> findAll() {
        return tareas;
    }

    public Optional<Tarea> findById(Long id) {
        return tareas.stream()
                .filter(t -> t.getId() != null && t.getId().equals(id))
                .findFirst();
    }

    public void save(Tarea tarea) {
        tarea.setId(contadorId++);
        tareas.add(tarea);
    }

    public boolean delete(Long id) {
        return tareas.removeIf(t -> t.getId() != null && t.getId().equals(id));
    }

    public List<Tarea> findByEstado(Estado estado) {
        return tareas.stream()
                .filter(t -> t.getEstado() != null && t.getEstado().equals(estado))
                .toList();
    }
}