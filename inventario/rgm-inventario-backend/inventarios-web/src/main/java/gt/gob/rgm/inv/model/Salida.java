package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SALIDA database table.
 * 
 */
@Entity
@NamedQuery(name="Salida.findAll", query="SELECT s FROM Salida s")
public class Salida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SALIDA_ID_GENERATOR", sequenceName="SALIDA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SALIDA_ID_GENERATOR")
	@Column(name="SALIDA_ID")
	private long salidaId;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String observaciones;

	@Column(name="TIPO_SALIDA_ID")
	private BigDecimal tipoSalidaId;

	@Column(name="USUARIO_ID")
	private BigDecimal usuarioId;
	
	private String correlativo;
	
	@ManyToOne
	@JoinColumn(name="USUARIO_ID", insertable=false, updatable=false)
	private Usuario solicitante;
	
	@ManyToOne
	@JoinColumn(name="TIPO_SALIDA_ID", insertable=false, updatable=false)
	private TipoSalida tipoSalida;

	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="SALIDA_ID")
	private List<DetalleSalida> detalle;

	public Salida() {
	}

	public long getSalidaId() {
		return this.salidaId;
	}

	public void setSalidaId(long salidaId) {
		this.salidaId = salidaId;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getTipoSalidaId() {
		return this.tipoSalidaId;
	}

	public void setTipoSalidaId(BigDecimal tipoSalidaId) {
		this.tipoSalidaId = tipoSalidaId;
	}

	public BigDecimal getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(BigDecimal usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public List<DetalleSalida> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleSalida> detalle) {
		this.detalle = detalle;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public TipoSalida getTipoSalida() {
		return tipoSalida;
	}

	public void setTipoSalida(TipoSalida tipoSalida) {
		this.tipoSalida = tipoSalida;
	}

}
