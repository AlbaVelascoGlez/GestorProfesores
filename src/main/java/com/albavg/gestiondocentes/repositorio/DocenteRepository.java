package com.albavg.gestiondocentes.repositorio;

import com.albavg.gestiondocentes.modelo.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    
    // Docentes ordenados de manera ascendente
    List<Docente> findAllByOrderByApellidosAsc();
    
    // Docentes de un departamento
    List<Docente> findByDepartamentoNombre(String nombreDepartamento);
}