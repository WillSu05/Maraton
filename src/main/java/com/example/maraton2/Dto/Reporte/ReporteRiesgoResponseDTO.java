package com.example.maraton2.Dto.Reporte;

import com.example.maraton2.Entity.CategoriaRiesgo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReporteRiesgoResponseDTO {

    private Long id;
    private String municipio;
    private String puntoReferencia;
    private CategoriaRiesgo categoriaRiesgo;
    private Double latitud;
    private Double longitud;
    private String descripcionAdicional;
    private LocalDateTime fechaCreacion;
    private String nombreUsuario;
    private Double distanciaKm;
}