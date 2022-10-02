package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import proyecto.mingeso.muebles_stgo.entities.RelojEntity;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.RelojRepository;
import proyecto.mingeso.muebles_stgo.services.RelojService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RelojServiceTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RelojRepository relojRepository;
    RelojEntity marca = new RelojEntity();
    RelojService relojService = new RelojService();

    @Test
    void obtenerMarcas() {
        ArrayList<RelojEntity> marcas = new ArrayList<>();
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setRut_empleado("20.580.291-6");
        marca.setHora(LocalTime.parse("08:00"));
        marcas.add(marca);
        entityManager.persistAndFlush(marca);
        assertThat(relojRepository.findAll()).isEqualTo(marcas);
    }
    @Test
    void crearMarca() {
        marca.setFecha(LocalDate.parse("2022-09-20"));
        marca.setRut_empleado("20.580.291-6");
        marca.setHora(LocalTime.parse("08:00"));
        marca.setId_marca(1L);
        RelojEntity marca1 = relojService.crearMarca("20.580.291-6", (LocalDate.parse("2022-09-20")), (LocalTime.parse("08:00")));
        relojRepository.save(marca1);
        assertThat(marca1).isEqualTo(marca);
    }
}