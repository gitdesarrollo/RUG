package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPO_INGRESO database table.
 * 
 */
@Entity
@Table(name="TIPO_INGRESO")
@NamedQuery(name="TipoIngreso.findAll", query="SELECT t FROM TipoIngreso t")
public class TipoIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOINGRESO_ID_GENERATOR", sequenceName="TIPOINGRESO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOINGRESO_ID_GENERATOR")
	@Column(name="TIPO_INGRESO_ID")
	private long tipoIngresoId;

	private String nombre;
	
	private String estado;

	public TipoIngreso() {
	}

	public long getTipoIngresoId() {
		return this.tipoIngresoId;
	}

	public void setTipoIngresoId(long tipoIngresoId) {
		this.tipoIngresoId = tipoIngresoId;
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