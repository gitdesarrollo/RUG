package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADO_OPERACION database table.
 * 
 */
@Entity
@Table(name="ESTADO_OPERACION")
@NamedQuery(name="EstadoOperacion.findAll", query="SELECT e FROM EstadoOperacion e")
public class EstadoOperacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ESTADO_OPERACION_ID")
	private long estadoOperacionId;

	private String nombre;

	public EstadoOperacion() {
	}

	public long getEstadoOperacionId() {
		return this.estadoOperacionId;
	}

	public void setEstadoOperacionId(long estadoOperacionId) {
		this.estadoOperacionId = estadoOperacionId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}