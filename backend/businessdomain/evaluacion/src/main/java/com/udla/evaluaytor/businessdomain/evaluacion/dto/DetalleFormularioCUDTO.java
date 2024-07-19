package com.udla.evaluaytor.businessdomain.evaluacion.dto;

import lombok.Data;

@Data
public class DetalleFormularioCUDTO {
    private Long id;
    private int cumplimiento;
    private String observacion;
    private Long estadoDetalleId;
    private Long documentoId;
    private Long formularioId; 
}
