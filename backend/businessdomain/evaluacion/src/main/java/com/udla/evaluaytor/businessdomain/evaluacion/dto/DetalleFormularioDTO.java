package com.udla.evaluaytor.businessdomain.evaluacion.dto;

import lombok.Data;

@Data
public class DetalleFormularioDTO {
    private Long id;
    private int cumplimiento;
    private String observacion;
    private DocumentoDTO documentoDTO;
    private EstadoDetalleDTO estadoDetalleDTO;
    private FormularioDTO formularioDTO;
}