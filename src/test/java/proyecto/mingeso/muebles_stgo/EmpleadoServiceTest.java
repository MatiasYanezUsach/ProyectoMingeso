package proyecto.mingeso.muebles_stgo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import proyecto.mingeso.muebles_stgo.entities.EmpleadoEntity;
import proyecto.mingeso.muebles_stgo.repositories.EmpleadoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpleadoServiceTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    void obtenerEmpleados() {
        EmpleadoEntity empleado = new EmpleadoEntity();
        ArrayList<EmpleadoEntity> empleados = new ArrayList<>();
        empleado.setRut("20.580.291-6");
        empleado.setFecha_nac(LocalDate.parse("2001-09-20"));
        empleado.setCategoria("A");
        empleado.setApellidos("Yanez");
        empleado.setNombres("Matias");
        empleado.setFecha_in(LocalDate.parse("2018-09-20"));
        empleados.add(empleado);
        entityManager.persistAndFlush(empleado);
        assertThat(empleadoRepository.findAll()).isEqualTo(empleados);
    }
}
