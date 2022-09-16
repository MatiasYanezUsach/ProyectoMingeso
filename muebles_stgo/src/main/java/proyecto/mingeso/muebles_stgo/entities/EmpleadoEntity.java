package proyecto.mingeso.muebles_stgo.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "empleados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idempleado", nullable = false)
    private Long idempleado;
    private String rut;
    private String apellidos;
    private String nombres;
    private String fechanac;
    private String categoria;
    private String fechain;
}
