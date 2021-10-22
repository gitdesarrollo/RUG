package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPO_SALIDA database table.
 * 
 */
@Entity
@Table(name="TIPO_SALIDA")
@NamedQuery(name="TipoSalida.findAll", query="SELECT t FROM TipoSalida t")
public class TipoSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOSALIDA_ID_GENERATOR", sequenceName="TIPOSALIDA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOSALIDA_ID_GENERATOR")
	@Column(name="TIPO_SALIDA_ID")
	private long tipoSalidaId;

	private String nombre;
	
	private String estado;

	public TipoSalida() {
	}

	public long getTipoSalidaId() {
		return this.tipoSalidaId;
	}

	public void setTipoSalidaId(long tipoSalidaId) {
		this.tipoSalidaId = tipoSalidaId;
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