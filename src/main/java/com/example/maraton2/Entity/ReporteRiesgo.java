package com.example.maraton2.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_riesgo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String municipio;

    @Column(name = "punto_referencia", nullable = false)
    private String puntoReferencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_riesgo", nullable = false)
    private CategoriaRiesgo categoriaRiesgo;

    private Double latitud;
    private Double longitud;

    @Column(columnDefinition = "TEXT")
    private String descripcionAdicional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
