package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the INGRESO database table.
 * 
 */@Entity
@NamedQuery(name="Ingreso.findAll", query="SELECT i FROM Ingreso i")
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INGRESO_ID_GENERATOR", sequenceName="INGRESO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INGRESO_ID_GENERATOR")
	@Column(name="INGRESO_ID")
	private long ingresoId;

	private String correlativo;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String observaciones;

	private String referencia;

	@Column(name="TIPO_INGRESO_ID")
	private BigDecimal tipoIngresoId;

	@Column(name="USUARIO_ID")
	private BigDecimal usuarioId;
	
	@ManyToOne
	@JoinColumn(name="USUARIO_ID", insertable=false, updatable=false)
	private Usuario solicitante;
	
	@ManyToOne
	@JoinColumn(name="TIPO_INGRESO_ID", insertable=false, updatable=false)
	private TipoIngreso tipoIngreso;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="INGRESO_ID")
	private List<DetalleIngreso> detalle;

	public Ingreso() {
	}

	public long getIngresoId() {
		return this.ingresoId;
	}

	public void setIngresoId(long ingresoId) {
		this.ingresoId = ingresoId;
	}

	public String getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
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

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getTipoIngresoId() {
		return this.tipoIngresoId;
	}

	public void setTipoIngresoId(BigDecimal tipoIngresoId) {
		this.tipoIngresoId = tipoIngresoId;
	}

	public BigDecimal getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(BigDecimal usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<DetalleIngreso> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleIngreso> detalle) {
		this.detalle = detalle;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public TipoIngreso getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

}