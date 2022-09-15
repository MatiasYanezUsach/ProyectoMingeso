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

    public Optional<EmpleadoEntity> obtenerPorId(Long id_empleado){
        return empleadoRepository.findById(id_empleado);
    }

    public boolean eliminarEmpleado(Long id_empleado){
        try{
            empleadoRepository.deleteById(id_empleado);
            return true;
        }
        catch(Exception err){
            return false;
        }
    }
}
