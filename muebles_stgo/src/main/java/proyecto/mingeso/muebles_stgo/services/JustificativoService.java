package proyecto.mingeso.muebles_stgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;


@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;

    public JustificativoEntity crearJustificativo(JustificativoEntity justificativo){
        return justificativoRepository.save(new JustificativoEntity(justificativo.getId_justificativo(), justificativo.getEmpresa_emisora(), justificativo.getFirma(),justificativo.getMotivo(), justificativo.getFecha_emision(), justificativo.getFecha_cubridora(),justificativo.getRut_empleado()));
    }
}
