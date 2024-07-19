package com.udla.evaluaytor.businessdomain.evaluacion.services;

import java.util.List;

import com.udla.evaluaytor.businessdomain.evaluacion.dto.DetalleFormularioCUDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.DetalleFormularioDTO;

public interface DetalleFormularioService {
    List<DetalleFormularioDTO> getAllDetallesFormulario();
    DetalleFormularioDTO getDetalleFormularioById(Long id);
    DetalleFormularioDTO createDetalleFormulario(DetalleFormularioCUDTO detalleFormularioDTO);
    DetalleFormularioDTO updateDetalleFormulario(Long id, DetalleFormularioCUDTO detalleFormularioDTO);
    void deleteDetalleFormulario(Long id);
}