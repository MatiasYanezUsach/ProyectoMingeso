package proyecto.mingeso.muebles_stgo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;

import java.util.ArrayList;

@Repository
public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {
    @Query(value="select j.fecha_cubridora from justificativos as j where j.rut_empleado = :rutDado",nativeQuery = true)
    ArrayList<JustificativoEntity> findByRut(@Param("rutDado") String rutDado);
}
