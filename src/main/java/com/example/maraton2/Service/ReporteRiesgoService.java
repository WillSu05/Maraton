package com.example.maraton2.Service;

import com.example.maraton2.Dto.Reporte.ReporteRiesgoCreateDTO;
import com.example.maraton2.Dto.Reporte.ReporteRiesgoResponseDTO;
import com.example.maraton2.Entity.CategoriaRiesgo;

import java.util.List;

public interface ReporteRiesgoService {
    ReporteRiesgoResponseDTO crearReporte(ReporteRiesgoCreateDTO dto, String emailUsuario);

    List<ReporteRiesgoResponseDTO> listarReportes(CategoriaRiesgo categoria, Double latOrigen, Double lonOrigen);

    ReporteRiesgoResponseDTO obtenerPorId(Long id);
}
