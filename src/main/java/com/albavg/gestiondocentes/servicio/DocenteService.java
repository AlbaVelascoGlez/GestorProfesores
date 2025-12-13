package com.albavg.gestiondocentes.servicio;

import com.albavg.gestiondocentes.modelo.Docente;
import com.albavg.gestiondocentes.repositorio.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    
    @Autowired
    private DocenteRepository docenteRepository;
    
    // Obtener docente por id
    public Optional<Docente> obtenerPorId(Long id) {
        return docenteRepository.findById(id);
    }
    
    // Docentes ordenados por apellidos de manera ascendente
    public List<Docente> obtenerDocentesOrdenadosPorApellidos() {
        return docenteRepository.findAllByOrderByApellidosAsc();
    }
    
    // Docentes de un departamento
    public List<Docente> obtenerDocentesPorDepartamento(String nombreDepartamento) {
        return docenteRepository.findByDepartamentoNombre(nombreDepartamento);
    }
}