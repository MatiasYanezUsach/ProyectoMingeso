package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @PostMapping("/subirJustificativo")
    public String subirJustificativo(@ModelAttribute JustificativoEntity justificativo, Model model) {
        model.addAttribute("justificado", justificativo);
        justificativoService.guardarJustificativo(justificativo);
        System.out.println(justificativo.getId_justificativo());
        System.out.println(justificativo.getEmpresa_emisora());
        System.out.println(justificativo.getFecha_cubridora());
        System.out.println(justificativo.getFecha_emision());
        System.out.println(justificativo.getMotivo());
        System.out.println(justificativo.getFirma());
        System.out.println(justificativo.getRut_empleado());
        return "redirect:/justificativo";
    }

    /*@PostMapping(value ="/subirJustificativo")
    public String subirJustificativo(@ModelAttribute("justificado") JustificativoEntity justificativo) {
        justificativoService.guardarJustificativo(justificativo);
        return "redirect:/justificativo";
    }*/
}