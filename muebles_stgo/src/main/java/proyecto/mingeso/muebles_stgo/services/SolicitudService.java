package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;

    public SolicitudEntity guardarSolicitud(SolicitudEntity solicitud){
        return solicitudRepository.save(solicitud);
    }
    public SolicitudEntity crearSolicitud(SolicitudEntity solicitud){
        SolicitudEntity nuevaSolicitud = solicitudRepository.save(new SolicitudEntity(solicitud.getId_solicitud(), solicitud.getCorreo_jefatura(), solicitud.getFecha_emision(), solicitud.getFecha_cubridora(), solicitud.getEmpleado()));
        return guardarSolicitud(nuevaSolicitud);
    }
}