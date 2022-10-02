package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import proyecto.mingeso.muebles_stgo.entities.JustificativoEntity;
import proyecto.mingeso.muebles_stgo.repositories.JustificativoRepository;
import proyecto.mingeso.muebles_stgo.services.JustificativoService;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JustificativoServiceTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private JustificativoRepository justificativoRepository;
    JustificativoService justificativoService = new JustificativoService();

    @Test
    void crearJustificativo() {
        JustificativoEntity justificativo1 = new JustificativoEntity();
        justificativo1.setFecha_cubridora(LocalDate.parse("2022-09-20"));
        justificativo1.setRut_empleado("20.580.291-6");
        justificativo1.setId_justificativo(1L);
        JustificativoEntity justificativo = justificativoService.crearJustificativo("20.580.291-6", (LocalDate.parse("2022-09-20")));
        justificativoRepository.save(justificativo);
        assertThat(justificativo).isEqualTo(justificativo1);
    }
}
