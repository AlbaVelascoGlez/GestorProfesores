package com.albavg.gestiondocentes.repositorio;

import com.albavg.gestiondocentes.modelo.AsuntoPropio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsuntoPropioRepository extends JpaRepository<AsuntoPropio, Long> {

    // Consultar días propios de un docente 
    List<AsuntoPropio> findByDocenteId(Long docenteId);

    // Días propios pendientes de disfrutar
    List<AsuntoPropio> findByDocenteIdAndAprobadoTrueAndDiaSolicitadoGreaterThanEqual(
        Long docenteId, LocalDate fecha);

    // Verificar si un docente ya ha solicitado un día propio en una fecha específica
    boolean existsByDocenteIdAndDiaSolicitado(Long docenteId, LocalDate fecha);

    // Contar días propios aprobados y disfrutados por docente
    Long countByDocenteIdAndAprobadoTrueAndDiaSolicitadoLessThan(Long docenteId, LocalDate fecha);

    // Obtener todos los asuntos propios aprobados y disfrutados (fecha < hoy)
    @Query("SELECT a FROM AsuntoPropio a WHERE a.aprobado = true AND a.diaSolicitado < :fecha ORDER BY a.docente.id")
    List<AsuntoPropio> findAllAprobadosYDisfrutados(@Param("fecha") LocalDate fecha);
}