package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import proyecto.mingeso.muebles_stgo.entities.RelojEntity;
import proyecto.mingeso.muebles_stgo.services.RelojService;

import java.util.ArrayList;

@RestController
public class RelojController {

    @Autowired
    private RelojService relojService;

    @RequestMapping(value = "/marcas", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<RelojEntity>> obtenerMarcas() {
        ArrayList<RelojEntity> marcas = relojService.obtenerMarcas();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }
}
