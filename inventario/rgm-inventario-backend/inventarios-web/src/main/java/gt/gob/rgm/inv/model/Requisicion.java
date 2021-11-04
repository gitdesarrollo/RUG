package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the REQUISICION database table.
 * 
 */
@Entity
@NamedQuery(name="Requisicion.findAll", query="SELECT r FROM Requisicion r")
public class Requisicion implements Serializable {
	private static final long serialVersionUID = 1L;
        
	@Id
	@SequenceGenerator(name="REQUISICION_ID_GENERATOR", sequenceName="REQUISICION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUISICION_ID_GENERATOR")
	@Column(name="REQUISICION_ID")
	private Long requisicionId;

	private String comentario;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String observaciones;

	@Column(name="USUARIO_ID")
	private BigDecimal usuarioId;
	
	private String correlativo;

	@ManyToOne
	@JoinColumn(name="USUARIO_ID", insertable=false, updatable=false)
	private Usuario solicitante;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="REQUISICION_ID")
	private List<DetalleRequisicion> detalle;

        @Transient
        private List<Long> articulosEliminados;
        
	public Requisicion() {
	}

	public Long getRequisicionId() {
		return this.requisicionId;
	}

	public void setRequisicionId(Long requisicionId) {
		this.requisicionId = requisicionId;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public List<DetalleRequisicion> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleRequisicion> detalle) {
		this.detalle = detalle;
	}
        
        public List<Long> getArticulosEliminados() {
		return articulosEliminados;
	}

	public void setArticulosEliminados(List<Long> articulos_eliminados) {
		this.articulosEliminados = articulos_eliminados;
	}

}