package com.example.maraton2.Repository;

import com.example.maraton2.Entity.CategoriaRiesgo;
import com.example.maraton2.Entity.ReporteRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRiesgoRepository extends JpaRepository <ReporteRiesgo, Long> {

    List<ReporteRiesgo> findByCategoriaRiesgo(CategoriaRiesgo categoriaRiesgo);

    List<ReporteRiesgo> findByMunicipioIgnoreCase(String municipio);
}
