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
    @Column(name = "id_empleado", nullable = false)
    private Long id_empleado;
    private String rut;
    private String apellidos;
    private String nombres;
    private String fecha_nac;
    private String categoria;
    private String fecha_in;
    @OneToOne(mappedBy = "empleado")
    private JustificativoEntity justificativo;
    @OneToOne(mappedBy = "empleado")
    private SolicitudEntity solicitud;
}
