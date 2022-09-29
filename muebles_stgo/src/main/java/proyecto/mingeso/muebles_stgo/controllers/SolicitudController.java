package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.services.SolicitudService;

import java.time.LocalDate;

@Controller
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("/subirSolicitud")
    public String subirSolicitud(@RequestParam("rut") String rut_empleado, @RequestParam("fecha") String fecha_cubridora) {
        SolicitudEntity nuevaSolicitud = solicitudService.crearSolicitud(rut_empleado, LocalDate.parse(fecha_cubridora));
        solicitudService.guardarSolicitud(nuevaSolicitud);
        return "redirect:/";
    }
}