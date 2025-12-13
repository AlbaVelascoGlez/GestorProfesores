package com.albavg.gestiondocentes.servicio;

import com.albavg.gestiondocentes.modelo.AsuntoPropio;
import com.albavg.gestiondocentes.modelo.Docente;
import com.albavg.gestiondocentes.repositorio.AsuntoPropioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AsuntoPropioService {
    
    @Autowired
    private AsuntoPropioRepository asuntoPropioRepository;
    
    @Autowired
    private DocenteService docenteService;
    
    
    public boolean solicitarDiaPropio(Long docenteId, LocalDate fecha, String descripcion) {
    	
        // Validar que el docente existe
        Docente docente = docenteService.obtenerPorId(docenteId).orElse(null);
        if (docente == null) {
            return false;
        }

        // Validar que la fecha sea válida (no puede ser en el pasado)
        if (fecha.isBefore(LocalDate.now())) {
            return false;
        }

        // Validar que el docente no haya solicitado ya ese día
        if (asuntoPropioRepository.existsByDocenteIdAndDiaSolicitado(docenteId, fecha)) {
            return false;
        }

        // Crear el asunto propio
        AsuntoPropio asuntoPropio = new AsuntoPropio();
        asuntoPropio.setDiaSolicitado(fecha);
        asuntoPropio.setDescripcion(descripcion);
        asuntoPropio.setAprobado(false);
        asuntoPropio.setDocente(docente);

        asuntoPropioRepository.save(asuntoPropio);
        return true;
    }
    
    
    public boolean validarDiaPropio(Long asuntoPropioId, boolean aceptado) {
        AsuntoPropio asuntoPropio = asuntoPropioRepository.findById(asuntoPropioId).orElse(null);
        if (asuntoPropio == null) {
            return false;
        }
        
        asuntoPropio.setAprobado(aceptado);
        asuntoPropio.setFechaTramitacion(LocalDate.now());
        asuntoPropioRepository.save(asuntoPropio);
        return true;
    }
  
    
    public List<AsuntoPropio> consultarDiasPropios(Long docenteId) {
        return asuntoPropioRepository.findByDocenteId(docenteId);
    }

    // Obtener días propios pendientes (validados y fecha >= hoy)
    public List<AsuntoPropio> obtenerDiasPendientesDisfrutar(Long docenteId) {
        return asuntoPropioRepository.findByDocenteIdAndAprobadoTrueAndDiaSolicitadoGreaterThanEqual(
            docenteId, LocalDate.now());
    }

    // Obtener el docente que más días ha disfrutado
    public Docente obtenerDocenteConMasDiasDisfrutados() {
        List<AsuntoPropio> asuntosPropios = asuntoPropioRepository.findAllAprobadosYDisfrutados(LocalDate.now());

        if (asuntosPropios.isEmpty()) {
            return null;
        }

        // Contar días disfrutados por cada docente
        Docente docenteConMasDias = null;
        long maxDias = 0;

        // Agrupar por docente y contar
        java.util.Map<Long, Long> conteo = new java.util.HashMap<>();
        java.util.Map<Long, Docente> docentes = new java.util.HashMap<>();

        for (AsuntoPropio asunto : asuntosPropios) {
            Long docenteId = asunto.getDocente().getId();
            conteo.put(docenteId, conteo.getOrDefault(docenteId, 0L) + 1);
            docentes.put(docenteId, asunto.getDocente());
        }

        // Encontrar el docente con más días
        for (java.util.Map.Entry<Long, Long> entry : conteo.entrySet()) {
            if (entry.getValue() > maxDias) {
                maxDias = entry.getValue();
                docenteConMasDias = docentes.get(entry.getKey());
            }
        }

        return docenteConMasDias;
    }
}