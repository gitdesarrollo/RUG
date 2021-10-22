package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the DESPACHO database table.
 * 
 */
@Entity
@NamedQuery(name="Despacho.findAll", query="SELECT d FROM Despacho d")
public class Despacho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DESPACHO_ID_GENERATOR", sequenceName="DESPACHO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DESPACHO_ID_GENERATOR")
	@Column(name="DESPACHO_ID")
	private Long despachoId;

	private BigDecimal contador;

	private String correlativo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String estado;

	private String observaciones;

	@Column(name="REQUISICION_ID")
	private BigDecimal requisicionId;
	
	@ManyToOne
	@JoinColumn(name="REQUISICION_ID", insertable=false, updatable=false)
	private Requisicion requisicion;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="DESPACHO_ID")
	private List<DetalleDespacho> detalle;

	public Despacho() {
	}

	public Long getDespachoId() {
		return this.despachoId;
	}

	public void setDespachoId(Long despachoId) {
		this.despachoId = despachoId;
	}

	public BigDecimal getContador() {
		return this.contador;
	}

	public void setContador(BigDecimal contador) {
		this.contador = contador;
	}

	public String getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getRequisicionId() {
		return this.requisicionId;
	}

	public void setRequisicionId(BigDecimal requisicionId) {
		this.requisicionId = requisicionId;
	}

	public List<DetalleDespacho> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleDespacho> detalle) {
		this.detalle = detalle;
	}

	public Requisicion getRequisicion() {
		return requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

}