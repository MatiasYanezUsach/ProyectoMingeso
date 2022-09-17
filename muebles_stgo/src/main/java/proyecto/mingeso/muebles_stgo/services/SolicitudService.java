package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;

    public ArrayList<SolicitudEntity> obtenerSolicitudes(){
        return (ArrayList<SolicitudEntity>) solicitudRepository.findAll();
    }
    public SolicitudEntity guardarSolicitud(SolicitudEntity solicitud){
        return solicitudRepository.save(solicitud);
    }
    public SolicitudEntity crearSolicitud(SolicitudEntity solicitud){
        SolicitudEntity nuevaSolicitud = solicitudRepository.save(new SolicitudEntity(solicitud.getId_solicitud(), solicitud.getCorreo_jefatura(), solicitud.getFecha_emision(), solicitud.getFecha_cubridora(), solicitud.getEmpleado()));
        return guardarSolicitud(nuevaSolicitud);
    }

    public Optional<SolicitudEntity> obtenerPorId(Long id_solicitud){
        return solicitudRepository.findById(id_solicitud);
    }

    public SolicitudEntity modificarSolicitud(Long id_solicitud, SolicitudEntity solicitud){
        Optional<SolicitudEntity> solicitudPrev=obtenerPorId(id_solicitud);
        SolicitudEntity nuevaSolicitud = solicitudRepository.save(new SolicitudEntity(id_solicitud, solicitud.getCorreo_jefatura(), solicitud.getFecha_emision(), solicitud.getFecha_cubridora(), solicitud.getEmpleado()));
        return guardarSolicitud(nuevaSolicitud);
    }

    public boolean eliminarSolicitud(Long id_solicitud){
        try{
            solicitudRepository.deleteById(id_solicitud);
            return true;
        }
        catch(Exception err){
            return false;
        }
    }
}