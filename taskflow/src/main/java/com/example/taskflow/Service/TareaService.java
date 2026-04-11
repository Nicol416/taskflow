package com.example.taskflow.Service;

import com.example.taskflow.Model.Estado;
import com.example.taskflow.Model.Tarea;
import com.example.taskflow.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public boolean guardar(Tarea nuevaTarea) {
        if (nuevaTarea.getTitulo() == null || nuevaTarea.getTitulo().trim().isEmpty()) {
            return false;
        }

        if (nuevaTarea.getDescripcion() == null || nuevaTarea.getDescripcion().trim().isEmpty()) {
            return false;
        }

        if (nuevaTarea.getResponsable() == null || nuevaTarea.getResponsable().trim().isEmpty()) {
            return false;
        }

        if (nuevaTarea.getEstado() == null) {
            return false;
        }

        if (nuevaTarea.getPrioridad() == null) {
            return false;
        }

        if (nuevaTarea.getFechaLimite() == null) {
            return false;
        }

        nuevaTarea.setFechaCreacion(LocalDate.now());
        tareaRepository.save(nuevaTarea);
        return true;
    }

    public boolean actualizar(Long id, Tarea tareaActualizada) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(id);

        if (tareaOpt.isEmpty()) {
            return false;
        }

        if (tareaActualizada.getTitulo() == null || tareaActualizada.getTitulo().trim().isEmpty()) {
            return false;
        }

        if (tareaActualizada.getDescripcion() == null || tareaActualizada.getDescripcion().trim().isEmpty()) {
            return false;
        }

        if (tareaActualizada.getResponsable() == null || tareaActualizada.getResponsable().trim().isEmpty()) {
            return false;
        }

        if (tareaActualizada.getEstado() == null) {
            return false;
        }

        if (tareaActualizada.getPrioridad() == null) {
            return false;
        }

        if (tareaActualizada.getFechaLimite() == null) {
            return false;
        }

        Tarea tarea = tareaOpt.get();
        tarea.setTitulo(tareaActualizada.getTitulo());
        tarea.setDescripcion(tareaActualizada.getDescripcion());
        tarea.setEstado(tareaActualizada.getEstado());
        tarea.setPrioridad(tareaActualizada.getPrioridad());
        tarea.setResponsable(tareaActualizada.getResponsable());
        tarea.setFechaLimite(tareaActualizada.getFechaLimite());

        return true;
    }

    public boolean eliminar(Long id) {
        return tareaRepository.delete(id);
    }

    public List<Tarea> listarPorEstado(Estado estado) {
        return tareaRepository.findAll().stream()
                .filter(t -> t.getEstado().equals(estado))
                .collect(Collectors.toList());
    }
}