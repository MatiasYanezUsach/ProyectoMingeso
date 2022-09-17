package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;

    public ArrayList<JustificativoEntity> obtenerJustificativos(){
        return (ArrayList<JustificativoEntity>) justificativoRepository.findAll();
    }

    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo){
        return justificativoRepository.save(justificativo);
    }

    public JustificativoEntity crearJustificativo(JustificativoEntity justificativo){
        JustificativoEntity nuevoJustificativo = justificativoRepository.save(new JustificativoEntity(justificativo.getId_justificativo(), justificativo.getEmpresa_emisora(), justificativo.getFirma(),justificativo.getMotivo(), justificativo.getFecha_emision(), justificativo.getFecha_cubridora(),justificativo.getEmpleado()));
        return guardarJustificativo(nuevoJustificativo);
    }

    public Optional<JustificativoEntity> obtenerPorId(Long idjustificativo){
        return justificativoRepository.findById(idjustificativo);
    }

    public JustificativoEntity modificarJustificativo(Long idjustificativo, JustificativoEntity justificativo){
        Optional<JustificativoEntity> justificativoPrev=obtenerPorId(idjustificativo);
        JustificativoEntity nuevoJustificativo = justificativoRepository.save(new JustificativoEntity(idjustificativo, justificativo.getEmpresa_emisora(), justificativo.getFirma(),justificativo.getMotivo(), justificativo.getFecha_emision(), justificativo.getFecha_cubridora(),justificativo.getEmpleado()));
        return guardarJustificativo(nuevoJustificativo);
    }

    public boolean eliminarJustificativo(Long idjustificativo){
        try{
            justificativoRepository.deleteById(idjustificativo);
            return true;
        }
        catch(Exception err){
            return false;
        }
    }
}
