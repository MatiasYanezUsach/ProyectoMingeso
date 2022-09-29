package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

import java.time.LocalDate;

@Controller
public class JustificativoController {

    @Autowired
    private JustificativoService justificativoService;

    @PostMapping("/subirJustificativo")
    public String subirJustificativo(@RequestParam("rut") String rut_empleado, @RequestParam("fecha") String fecha_cubridora) {
        JustificativoEntity nuevoJustificativo = justificativoService.crearJustificativo(rut_empleado, LocalDate.parse(fecha_cubridora));
        justificativoService.guardarJustificativo(nuevoJustificativo);
        return "redirect:/";
    }
}