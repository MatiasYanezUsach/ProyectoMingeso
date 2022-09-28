package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

@Controller
public class JustificativoController {

    @Autowired
    private JustificativoService justificativoService;
    @GetMapping("/justificativo")
    public String VistaJustificativo() {
        return "VistaJustificativo";
    }

    @PostMapping(value ="/subirJustificativo")
    public String subirJustificativo(@ModelAttribute("justificado") JustificativoEntity justificativo) {
        justificativoService.guardarJustificativo(justificativo);
        return "redirect:/justificativo";
    }
}