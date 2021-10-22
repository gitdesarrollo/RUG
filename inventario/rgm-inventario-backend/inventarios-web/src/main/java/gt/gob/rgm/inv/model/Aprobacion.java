package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the APROBACION database table.
 * 
 */
@Entity
@NamedQuery(name="Aprobacion.findAll", query="SELECT a FROM Aprobacion a")
public class Aprobacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APROBACION_ID_GENERATOR", sequenceName="APROBACION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APROBACION_ID_GENERATOR")
	@Column(name="APROBACION_ID")
	private Long aprobacionId;

	private String documento;

	private Timestamp fecha;

	@Column(name="USUARIO_ID")
	private BigDecimal usuarioId;

	public Aprobacion() {
	}

	public Long getAprobacionId() {
		return this.aprobacionId;
	}

	public void setAprobacionId(Long aprobacionId) {
		this.aprobacionId = aprobacionId;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(BigDecimal usuarioId) {
		this.usuarioId = usuarioId;
	}

}