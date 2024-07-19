package com.udla.evaluaytor.businessdomain.evaluacion.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.evaluaytor.businessdomain.evaluacion.dto.DetalleFormularioCUDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.DetalleFormularioDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.DocumentoDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.EstadoDetalleDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.FormularioDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.models.DetalleFormulario;
import com.udla.evaluaytor.businessdomain.evaluacion.models.Documento;
import com.udla.evaluaytor.businessdomain.evaluacion.models.EstadoDetalle;
import com.udla.evaluaytor.businessdomain.evaluacion.models.FormularioEvaluacion;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.DetalleFormularioRepository;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.DocumentoRepository;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.EstadoDetalleRepository;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.FormularioEvaluacionRepository;

@Service
public class DetalleFormularioServiceImpl implements DetalleFormularioService {

    @Autowired
    private DetalleFormularioRepository detalleFormularioRepository;

    @Autowired
    private FormularioEvaluacionRepository formularioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private EstadoDetalleRepository estadoDetalleRepository;

    @Override
    public List<DetalleFormularioDTO> getAllDetallesFormulario() {
        List<DetalleFormulario> detalles = detalleFormularioRepository.findAll();
        return detalles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DetalleFormularioDTO getDetalleFormularioById(Long id) {
        Optional<DetalleFormulario> detalleOpt = detalleFormularioRepository.findById(id);
        return detalleOpt.map(this::convertToDTO).orElse(null); 
    }

    @Override
    public DetalleFormularioDTO createDetalleFormulario(DetalleFormularioCUDTO detalleFormularioDTO) {
        DetalleFormulario detalle = convertToEntity(detalleFormularioDTO);
        DetalleFormulario savedDetalle = detalleFormularioRepository.save(detalle);
        return convertToDTO(savedDetalle);
    }

    @Override
    public DetalleFormularioDTO updateDetalleFormulario(Long id, DetalleFormularioCUDTO detalleFormularioDTO) {
        Optional<DetalleFormulario> detalleOpt = detalleFormularioRepository.findById(id);
        if (detalleOpt.isPresent()) {
            DetalleFormulario detalle = detalleOpt.get();
            detalle.setCumplimiento(detalleFormularioDTO.getCumplimiento());
            detalle.setObservacion(detalleFormularioDTO.getObservacion());

            Optional<EstadoDetalle> estadoOpt = estadoDetalleRepository.findById(detalleFormularioDTO.getEstadoDetalleId());
            estadoOpt.ifPresent(detalle::setEstadoDetalle);

            Optional<Documento> documentoOpt = documentoRepository.findById(detalleFormularioDTO.getDocumentoId());
            documentoOpt.ifPresent(detalle::setDocumento);

            Optional<FormularioEvaluacion> formularioOpt = formularioRepository.findById(detalleFormularioDTO.getFormularioId());
            formularioOpt.ifPresent(detalle::setFormulario);

            DetalleFormulario updatedDetalle = detalleFormularioRepository.save(detalle);
            return convertToDTO(updatedDetalle);
        }
        return null; 
    }

    @Override
    public void deleteDetalleFormulario(Long id) {
        detalleFormularioRepository.deleteById(id);
    }

    private DetalleFormularioDTO convertToDTO(DetalleFormulario detalle) {
        DetalleFormularioDTO dto = new DetalleFormularioDTO();
        dto.setId(detalle.getId());
        dto.setCumplimiento(detalle.getCumplimiento());
        dto.setObservacion(detalle.getObservacion());

        if (detalle.getEstadoDetalle() != null) {
            EstadoDetalleDTO estadoDTO = new EstadoDetalleDTO();
            estadoDTO.setId(detalle.getEstadoDetalle().getId());
            estadoDTO.setNombre(detalle.getEstadoDetalle().getNombre());
         
        }

        if (detalle.getDocumento() != null) {
            DocumentoDTO documentoDTO = new DocumentoDTO();
            documentoDTO.setId(detalle.getDocumento().getId());
            documentoDTO.setNombre(detalle.getDocumento().getNombre());
            documentoDTO.setPath(detalle.getDocumento().getPath());
            dto.setDocumentoDTO(documentoDTO);
        }

        if (detalle.getFormulario() != null) {
            FormularioDTO formularioDTO = new FormularioDTO();
            formularioDTO.setId(detalle.getFormulario().getId());
            formularioDTO.setNumero(detalle.getFormulario().getNumero());
            formularioDTO.setEvaluacion(detalle.getFormulario().getEvaluacion());

        

            dto.setFormularioDTO(formularioDTO);
        }

        return dto;
    }

    private DetalleFormulario convertToEntity(DetalleFormularioCUDTO dto) {
        DetalleFormulario detalle = new DetalleFormulario();
        detalle.setCumplimiento(dto.getCumplimiento());
        detalle.setObservacion(dto.getObservacion());

        Optional<EstadoDetalle> estadoOpt = estadoDetalleRepository.findById(dto.getEstadoDetalleId());
        estadoOpt.ifPresent(detalle::setEstadoDetalle);

        Optional<Documento> documentoOpt = documentoRepository.findById(dto.getDocumentoId());
        documentoOpt.ifPresent(detalle::setDocumento);

        Optional<FormularioEvaluacion> formularioOpt = formularioRepository.findById(dto.getFormularioId());
        formularioOpt.ifPresent(detalle::setFormulario);

        return detalle;
    }
}