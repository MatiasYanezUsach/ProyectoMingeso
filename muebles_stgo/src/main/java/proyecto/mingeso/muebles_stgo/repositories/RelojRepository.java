package proyecto.mingeso.muebles_stgo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.muebles_stgo.entities.RelojEntity;

import java.util.ArrayList;

@Repository
public interface RelojRepository extends CrudRepository<RelojEntity, Long> {
    @Query(value="select h.hora, h.fecha from marcas as h where h.rut = :rutDado", nativeQuery = true)
    ArrayList<RelojEntity> findByRut(@Param("rutDado") String rutDado);
}