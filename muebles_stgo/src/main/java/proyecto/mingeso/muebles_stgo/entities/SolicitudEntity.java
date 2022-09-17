package proyecto.mingeso.muebles_stgo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "solicitudes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_solicitud", nullable = false)
    private Long id_solicitud;
    private String correo_jefatura;
    private String fecha_emision;
    private String fecha_cubridora;
    @OneToOne
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;
}