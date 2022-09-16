package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.repositories.EmpleadoRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public ArrayList<EmpleadoEntity> obtenerEmpleados(){
        return (ArrayList<EmpleadoEntity>) empleadoRepository.findAll();
    }
    public EmpleadoEntity guardarEmpleado(EmpleadoEntity empleado){
        return empleadoRepository.save(empleado);
    }
    public EmpleadoEntity crearEmpleado(EmpleadoEntity empleado){
        EmpleadoEntity nuevoEmpleado = empleadoRepository.save(new EmpleadoEntity(empleado.getIdempleado(), empleado.getRut(), empleado.getApellidos(), empleado.getNombres(), empleado.getFechanac(), empleado.getCategoria(), empleado.getFechain()));
        return guardarEmpleado(nuevoEmpleado);
    }

    public Optional<EmpleadoEntity> obtenerPorId(Long idempleado){
        return empleadoRepository.findById(idempleado);
    }

    public EmpleadoEntity modificarEmpleado(Long idempleado, EmpleadoEntity empleado){
        Optional<EmpleadoEntity> empleadoPrev=obtenerPorId(idempleado);
        EmpleadoEntity nuevoEmpleado = empleadoRepository.save(new EmpleadoEntity(idempleado, empleado.getRut(), empleado.getApellidos(), empleado.getNombres(), empleado.getFechanac(), empleado.getCategoria(), empleado.getFechain()));
        return guardarEmpleado(nuevoEmpleado);
    }

    public boolean eliminarEmpleado(Long idempleado){
        try{
            empleadoRepository.deleteById(idempleado);
            return true;
        }
        catch(Exception err){
            return false;
        }
    }
}
