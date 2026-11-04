package com.example.maraton2.Controller;

import com.example.maraton2.Dto.Reporte.ReporteRiesgoCreateDTO;
import com.example.maraton2.Dto.Reporte.ReporteRiesgoResponseDTO;
import com.example.maraton2.Entity.CategoriaRiesgo;
import com.example.maraton2.Service.ReporteRiesgoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteRiesgoController {

    private final ReporteRiesgoService reporteService;

    @PostMapping
    public ResponseEntity<ReporteRiesgoResponseDTO> crearReporte(
            @Valid @RequestBody ReporteRiesgoCreateDTO dto,
            Authentication authentication) {

        String emailUsuario = authentication.getName();

        ReporteRiesgoResponseDTO reporte = reporteService.crearReporte(dto, emailUsuario);
        return new ResponseEntity<>(reporte, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReporteRiesgoResponseDTO>> listarReportes(
            @RequestParam(required = false) CategoriaRiesgo categoria,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {

        List<ReporteRiesgoResponseDTO> reportes = reporteService.listarReportes(categoria, lat, lon);
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteRiesgoResponseDTO> obtenerPorId(@PathVariable Long id) {
        ReporteRiesgoResponseDTO reporte = reporteService.obtenerPorId(id);
        return ResponseEntity.ok(reporte);
    }
}
