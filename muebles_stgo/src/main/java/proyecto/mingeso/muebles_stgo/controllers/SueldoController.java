package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.entities.SueldoEntity;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;
import proyecto.mingeso.muebles_stgo.services.SueldoService;

import java.util.ArrayList;

@RestController
public class SueldoController {
    @Autowired
    private SueldoService sueldoService;
    @Autowired
    private EmpleadoService empleadoService;

    @RequestMapping(value = "/planilla", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<SueldoEntity>> obtenerPlanillas() {
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        sueldoService.sueldosGenerales(empleados);
        ArrayList<SueldoEntity> sueldos = sueldoService.obtenerPlanilla();
        return new ResponseEntity<>(sueldos, HttpStatus.OK);
    }
}
