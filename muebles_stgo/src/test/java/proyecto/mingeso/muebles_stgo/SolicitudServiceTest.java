package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import proyecto.mingeso.muebles_stgo.entities.SolicitudEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;
import proyecto.mingeso.muebles_stgo.repositories.SolicitudRepository;
import proyecto.mingeso.muebles_stgo.services.SolicitudService;

import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SolicitudServiceTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SolicitudRepository solicitudRepository;
    SolicitudEntity solicitud = new SolicitudEntity();
    SolicitudService solicitudService = new SolicitudService();

    @Test
    void crearSolicitud() {
        solicitud.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        solicitud.setRut_empleado("20.580.291-6");
        solicitud.setId_solicitud(1L);
        SolicitudEntity solicitud1 = solicitudService.crearSolicitud("20.580.291-6", (LocalDate.parse("2022-09-20")));
        solicitudRepository.save(solicitud1);
        assertThat(solicitud1).isEqualTo(solicitud);
    }
}
