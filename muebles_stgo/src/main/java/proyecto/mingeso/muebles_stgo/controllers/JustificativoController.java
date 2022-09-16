package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class JustificativoController {

    @Autowired
    private JustificativoService justificativoService;

    @RequestMapping(value = "/justificativos", method = RequestMethod.GET)
    public ResponseEntity<JustificativoEntity> obtenerJustificativos() {
        ArrayList<JustificativoEntity> justificativos = justificativoService.obtenerJustificativos();
        return new ResponseEntity(justificativos, HttpStatus.OK);
    }

    @RequestMapping(value = "/justificativos/{id}", method = RequestMethod.GET)
    public ResponseEntity<JustificativoEntity> obtenerPorId(@PathVariable("id") long id) {
        Optional<JustificativoEntity> justificativo = justificativoService.obtenerPorId(id);
        return new ResponseEntity(justificativo, HttpStatus.OK);
    }
    @RequestMapping(value = "/justificativos/create", method = RequestMethod.POST)
    public ResponseEntity<JustificativoEntity> crearJustificativo(@RequestBody JustificativoEntity justificativo) {
        JustificativoEntity nuevoJustificativo = justificativoService.crearJustificativo(justificativo);
        return new ResponseEntity<>(nuevoJustificativo, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/justificativos/modificar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JustificativoEntity> actualizarJustificativo(@PathVariable("id") Long id ,@RequestBody JustificativoEntity justificativo) {
        JustificativoEntity nuevoJustificativo = justificativoService.modificarJustificativo(id, justificativo);
        return new ResponseEntity<>(nuevoJustificativo, HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/justificativos/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JustificativoEntity> removerJustificativo(@PathVariable("id") long id) {
        if(justificativoService.eliminarJustificativo(id) == true){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}