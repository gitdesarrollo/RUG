package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the unidad_medida database table.
 * 
 */
@Entity
@Table(name="unidad_medida")
@NamedQueries({
	@NamedQuery(name="UnidadMedida.findAll", query="SELECT u FROM UnidadMedida u WHERE u.estado = 'A' ORDER BY u.nombre"),
	@NamedQuery(name="UnidadMedida.countAll", query="SELECT COUNT(u) FROM UnidadMedida u WHERE u.estado = 'A'")
})
public class UnidadMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNIDADMEDIDA_ID_GENERATOR", sequenceName="UNIDADMEDIDA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDADMEDIDA_ID_GENERATOR")
	private Integer id;

	private String nombre;
	
	private String estado;

	public UnidadMedida() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
