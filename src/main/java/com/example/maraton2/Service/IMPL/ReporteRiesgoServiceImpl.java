package com.example.maraton2.Service.IMPL;

import com.example.maraton2.Dto.Reporte.ReporteRiesgoCreateDTO;
import com.example.maraton2.Dto.Reporte.ReporteRiesgoResponseDTO;
import com.example.maraton2.Entity.CategoriaRiesgo;
import com.example.maraton2.Entity.ReporteRiesgo;
import com.example.maraton2.Entity.Usuario;
import com.example.maraton2.Repository.ReporteRiesgoRepository;
import com.example.maraton2.Repository.UsuarioRepository;
import com.example.maraton2.Service.GeocodingService;
import com.example.maraton2.Service.ReporteRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteRiesgoServiceImpl implements ReporteRiesgoService {

    private final ReporteRiesgoRepository reporteRepository;
    private final UsuarioRepository usuarioRepository;
    private final GeocodingService geocodingService;
    @Override
    public ReporteRiesgoResponseDTO crearReporte(ReporteRiesgoCreateDTO dto, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Double lat = dto.getLatitud();
        Double lon = dto.getLongitud();


        if (lat == null || lon == null) {
            double[] coords = geocodingService.obtenerCoordenadas(dto.getMunicipio(), dto.getPuntoReferencia());
            lat = coords[0];
            lon = coords[1];
        }

        ReporteRiesgo reporte = ReporteRiesgo.builder()
                .municipio(dto.getMunicipio())
                .puntoReferencia(dto.getPuntoReferencia())
                .categoriaRiesgo(dto.getCategoriaRiesgo())
                .latitud(lat)
                .longitud(lon)
                .descripcionAdicional(dto.getDescripcionAdicional())
                .usuario(usuario)
                .build();

        ReporteRiesgo guardado = reporteRepository.save(reporte);
        return mapToDTO(guardado, null, null);
    }

    @Override
    public List<ReporteRiesgoResponseDTO> listarReportes(CategoriaRiesgo categoria, Double latOrigen, Double lonOrigen) {
        List<ReporteRiesgo> reportes;

        if (categoria != null) {
            reportes = reporteRepository.findByCategoriaRiesgo(categoria);
        } else {
            reportes = reporteRepository.findAll();
        }

        List<ReporteRiesgoResponseDTO> dtos = reportes.stream()
                .map(r -> mapToDTO(r, latOrigen, lonOrigen))
                .toList();

        if (latOrigen != null && lonOrigen != null) {
            dtos = dtos.stream()
                    .sorted(Comparator.comparing(ReporteRiesgoResponseDTO::getDistanciaKm, Comparator.nullsLast(Comparator.naturalOrder())))
                    .toList();
        }

        return dtos;
    }

    @Override
    public ReporteRiesgoResponseDTO obtenerPorId(Long id) {
        ReporteRiesgo reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado con ID: " + id));
        return mapToDTO(reporte, null, null);
    }

    private ReporteRiesgoResponseDTO mapToDTO(ReporteRiesgo entity, Double latOrigen, Double lonOrigen) {
        Double distancia = null;
        if (latOrigen != null && lonOrigen != null && entity.getLatitud() != null && entity.getLongitud() != null) {
            distancia = geocodingService.calcularDistanciaKm(latOrigen, lonOrigen, entity.getLatitud(), entity.getLongitud());
            distancia = Math.round(distancia * 100.0) / 100.0;
        }

        return ReporteRiesgoResponseDTO.builder()
                .id(entity.getId())
                .municipio(entity.getMunicipio())
                .puntoReferencia(entity.getPuntoReferencia())
                .categoriaRiesgo(entity.getCategoriaRiesgo())
                .latitud(entity.getLatitud())
                .longitud(entity.getLongitud())
                .descripcionAdicional(entity.getDescripcionAdicional())
                .fechaCreacion(entity.getFechaCreacion())
                .nombreUsuario(entity.getUsuario().getNombre())
                .distanciaKm(distancia)
                .build();
    }
}
