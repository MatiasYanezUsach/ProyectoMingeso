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
    @Column(name = "idjustificacion", nullable = false)
    private Long idjustificacion;
    private String empresaemisora;
    @Basic
    @Column(name = "firma", nullable = true)
    private String firma;
    private String motivo;
    private String fechaemision;
    private String fechacubridora;
}
