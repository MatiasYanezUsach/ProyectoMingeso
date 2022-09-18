package proyecto.mingeso.muebles_stgo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "marcas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelojEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_marca", nullable = false)
    private Long id_marca;
    private String fecha;
    private String hora;
    private String rut;
}