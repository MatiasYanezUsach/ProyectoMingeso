package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

@RestController
public class JustificativoController {

    @Autowired
    private JustificativoService justificativoService;

    @RequestMapping(value = "/justificativos/create", method = RequestMethod.POST)
    public ResponseEntity<JustificativoEntity> crearJustificativo(@RequestBody JustificativoEntity justificativo) {
        JustificativoEntity nuevoJustificativo = justificativoService.crearJustificativo(justificativo);
        return new ResponseEntity<>(nuevoJustificativo, HttpStatus.CREATED);
    }
}