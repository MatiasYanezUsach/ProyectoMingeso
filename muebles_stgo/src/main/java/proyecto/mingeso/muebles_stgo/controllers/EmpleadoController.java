package proyecto.mingeso.muebles_stgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.services.EmpleadoService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @RequestMapping(value = "/empleados", method = RequestMethod.GET)
    public ResponseEntity<EmpleadoEntity> obtenerEmpleados() {
        ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados();
        return new ResponseEntity(empleados, HttpStatus.OK);
    }

    @RequestMapping(value = "/empleados/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmpleadoEntity> obtenerPorId(@PathVariable("id") long id) {
        Optional<EmpleadoEntity> empleado = empleadoService.obtenerPorId(id);
        return new ResponseEntity(empleado, HttpStatus.OK);
    }
    @RequestMapping(value = "/empleados/create", method = RequestMethod.POST)
    public ResponseEntity<EmpleadoEntity> crearEmpleado(@RequestBody EmpleadoEntity empleado) {
        EmpleadoEntity nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/empleados/modificar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmpleadoEntity> actualizarEmpleado(@PathVariable("id") Long id ,@RequestBody EmpleadoEntity empleado) {
        EmpleadoEntity nuevoEmpleado = empleadoService.modificarEmpleado(id, empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/empleados/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EmpleadoEntity> removerEmpleado(@PathVariable("id") long id) {
        if(empleadoService.eliminarEmpleado(id) == true){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
