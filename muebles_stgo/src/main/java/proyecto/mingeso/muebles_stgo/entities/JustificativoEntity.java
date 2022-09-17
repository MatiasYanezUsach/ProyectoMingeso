package proyecto.mingeso.muebles_stgo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "justificativos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JustificativoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_justificativo", nullable = false)
    private Long id_justificativo;
    private String empresa_emisora;
    @Basic
    @Column(name = "firma", nullable = true)
    private String firma;
    private String motivo;
    private String fecha_emision;
    private String fecha_cubridora;
    @OneToOne
    @JoinColumn(name="id_empleado")
    private EmpleadoEntity empleado;
}
