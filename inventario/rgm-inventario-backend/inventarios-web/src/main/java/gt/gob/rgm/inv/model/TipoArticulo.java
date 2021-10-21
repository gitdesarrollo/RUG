package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_articulo database table.
 * 
 */
@Entity
@Table(name="tipo_articulo")
@NamedQuery(name="TipoArticulo.findAll", query="SELECT t FROM TipoArticulo t WHERE t.estado = 'A' ORDER BY t.nombre")
public class TipoArticulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOARTICULO_ID_GENERATOR", sequenceName="TIPOARTICULO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOARTICULO_ID_GENERATOR")
	private Integer id;

	private String nombre;
	
	private String estado;

	public TipoArticulo() {
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
