package com.albavg.gestiondocentes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO que combina información de AsuntoPropio y Docente
 * para mostrar detalles de solicitudes de días propios
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsuntoPropioDetalleDTO {

    // Datos del AsuntoPropio
    private Long asuntoPropioId;
    private LocalDate diaSolicitado;
    private LocalDate fechaTramitacion;
    private String descripcion;
    private Boolean aprobado;

    // Datos del Docente
    private Long docenteId;
    private String nombreDocente;
    private String apellidosDocente;
    private String emailDocente;
    private String siglasDocente;

    // Datos del Departamento
    private String nombreDepartamento;
    private String codigoDepartamento;

    // Datos del Rol
    private String nombreRol;
    private Integer ordenRol;
}
