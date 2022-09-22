package proyecto.mingeso.muebles_stgo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;

import java.util.ArrayList;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {
    @Query(value="select s.fecha_cubridora from solicitudes as s where s.rut_empleado = :rutDado",nativeQuery = true)
    ArrayList<SolicitudEntity> findByRut(@Param("rutDado") String rutDado);
}
