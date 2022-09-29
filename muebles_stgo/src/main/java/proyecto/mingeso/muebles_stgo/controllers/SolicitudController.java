package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.services.SolicitudService;

@RestController
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @RequestMapping(value = "/solicitudes/create", method = RequestMethod.POST)
    public ResponseEntity<SolicitudEntity> crearSolitud(@RequestBody SolicitudEntity solicitud) {
        SolicitudEntity nuevaSolicitud = solicitudService.guardarSolicitud(solicitud);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }
}