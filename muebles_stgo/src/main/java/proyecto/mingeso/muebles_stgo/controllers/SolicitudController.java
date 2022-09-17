package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.services.SolicitudService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @RequestMapping(value = "/solicitudes", method = RequestMethod.GET)
    public ResponseEntity<SolicitudEntity> obtenerSolicitudes() {
        ArrayList<SolicitudEntity> solicitudes = solicitudService.obtenerSolicitudes();
        return new ResponseEntity(solicitudes, HttpStatus.OK);
    }

    @RequestMapping(value = "/solicitudes/{id}", method = RequestMethod.GET)
    public ResponseEntity<SolicitudEntity> obtenerPorId(@PathVariable("id") long id) {
        Optional<SolicitudEntity> solicitud = solicitudService.obtenerPorId(id);
        return new ResponseEntity(solicitud, HttpStatus.OK);
    }
    @RequestMapping(value = "/solicitudes/create", method = RequestMethod.POST)
    public ResponseEntity<SolicitudEntity> crearSolitud(@RequestBody SolicitudEntity solicitud) {
        SolicitudEntity nuevaSolicitud = solicitudService.crearSolicitud(solicitud);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/solicitudes/modificar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SolicitudEntity> actualizarSolicitud(@PathVariable("id") Long id ,@RequestBody SolicitudEntity solicitud) {
        SolicitudEntity nuevaSolicitud = solicitudService.modificarSolicitud(id, solicitud);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/solicitudes/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SolicitudEntity> removerSolicitud(@PathVariable("id") long id) {
        if(solicitudService.eliminarSolicitud(id) == true){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}