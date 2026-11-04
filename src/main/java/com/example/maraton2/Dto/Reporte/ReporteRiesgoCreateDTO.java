package com.example.maraton2.Dto.Reporte;

import com.example.maraton2.Entity.CategoriaRiesgo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReporteRiesgoCreateDTO {

    @NotBlank(message = "El municipio es obligatorio")
    private String municipio;

    @NotBlank(message = "El punto de referencia es obligatorio")
    private String puntoReferencia;

    @NotNull(message = "La categoría de riesgo es obligatoria")
    private CategoriaRiesgo categoriaRiesgo;

    private Double latitud;
    private Double longitud;
    private String descripcionAdicional;
}
