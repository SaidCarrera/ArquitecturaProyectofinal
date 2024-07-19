package com.udla.evaluaytor.businessdomain.evaluacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udla.evaluaytor.businessdomain.evaluacion.dto.DetalleFormularioCUDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.DetalleFormularioDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.models.FormularioEvaluacion;
import com.udla.evaluaytor.businessdomain.evaluacion.services.DetalleFormularioService;
import com.udla.evaluaytor.businessdomain.evaluacion.services.FormularioEvaluacionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/evaluacion")
public class FormularioEvaluacionController {

    @Autowired
    private FormularioEvaluacionService formularioEvaluacionService;

    @GetMapping("/formulario/{formularioId}")
    public FormularioEvaluacion getFormularioEvaluacion(@PathVariable Long formularioId) {
        return formularioEvaluacionService.getFormularioEvaluacion(formularioId);
    }
   @Autowired
    private DetalleFormularioService detalleFormularioService;

    @GetMapping("/detalleformulario/findall")
    public List<DetalleFormularioDTO> getAllDetallesFormulario() {
        return detalleFormularioService.getAllDetallesFormulario();
    }

    @GetMapping("/detalleformulario/find/{id}")
    public ResponseEntity<DetalleFormularioDTO> getDetalleFormularioById(@PathVariable Long id) {
        DetalleFormularioDTO detalle = detalleFormularioService.getDetalleFormularioById(id);
        if (detalle != null) {
            return ResponseEntity.ok(detalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/detalleformulario/create")
    public ResponseEntity<DetalleFormularioDTO> createDetalleFormulario(@RequestBody DetalleFormularioCUDTO detalleFormularioDTO) {
        DetalleFormularioDTO createdDetalle = detalleFormularioService.createDetalleFormulario(detalleFormularioDTO);
        return ResponseEntity.ok(createdDetalle);
    }

    @PutMapping("/detalleformulario/update/{id}")
    public ResponseEntity<DetalleFormularioDTO> updateDetalleFormulario(@PathVariable Long id, @RequestBody DetalleFormularioCUDTO detalleFormularioDTO) {
        DetalleFormularioDTO updatedDetalle = detalleFormularioService.updateDetalleFormulario(id, detalleFormularioDTO);
        if (updatedDetalle != null) {
            return ResponseEntity.ok(updatedDetalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/detalleformulario/delete/{id}")
    public ResponseEntity<Void> deleteDetalleFormulario(@PathVariable Long id) {
        detalleFormularioService.deleteDetalleFormulario(id);
        return ResponseEntity.noContent().build();
    }


}
