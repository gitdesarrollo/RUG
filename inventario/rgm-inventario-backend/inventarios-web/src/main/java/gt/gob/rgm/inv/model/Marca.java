package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m WHERE m.estado = 'A' ORDER BY m.nombre"),
	@NamedQuery(name="Marca.countAll", query="SELECT COUNT(m) FROM Marca m WHERE m.estado = 'A'")
})
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MARCA_ID_GENERATOR", sequenceName="MARCA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MARCA_ID_GENERATOR")
	private Integer id;

	private String nombre;
	
	private String estado;

	public Marca() {
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
