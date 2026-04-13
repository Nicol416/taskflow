package com.example.taskflow.Controller;

import com.example.taskflow.Model.Estado;
import com.example.taskflow.Model.Tarea;
import com.example.taskflow.Service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador REST que expone los endpoints de tareas

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService service;

    @GetMapping
    public ResponseEntity<List<Tarea>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"Tarea no encontrada\"}"));
    }

    
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Tarea tarea) {
        boolean resultado = service.guardar(tarea);

        if (resultado) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarea);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"error\": \"Datos inválidos para crear la tarea\"}");
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Tarea tarea) {
        boolean resultado = service.actualizar(id, tarea);

        if (resultado) {
            return ResponseEntity.ok(tarea);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"Tarea no encontrada o datos inválidos\"}");
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> listarPorEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(service.listarPorEstado(estado));
    }
}
