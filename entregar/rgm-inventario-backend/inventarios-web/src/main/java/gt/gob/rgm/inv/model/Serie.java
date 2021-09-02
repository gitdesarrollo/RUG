package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SERIE database table.
 * 
 */
@Entity
@NamedQuery(name="Serie.findAll", query="SELECT s FROM Serie s")
public class Serie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SERIE_ID")
	private long serieId;

	private BigDecimal anio;

	private BigDecimal contador;

	private String descripcion;

	private BigDecimal estado;

	private String observaciones;
	
	@Lob
	private byte[] archivo;

	@Column(name="TIPO_SERIE")
	private BigDecimal tipoSerie;

	public Serie() {
	}

	public long getSerieId() {
		return this.serieId;
	}

	public void setSerieId(long serieId) {
		this.serieId = serieId;
	}

	public BigDecimal getAnio() {
		return this.anio;
	}

	public void setAnio(BigDecimal anio) {
		this.anio = anio;
	}

	public BigDecimal getContador() {
		return this.contador;
	}

	public void setContador(BigDecimal contador) {
		this.contador = contador;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getEstado() {
		return this.estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getTipoSerie() {
		return this.tipoSerie;
	}

	public void setTipoSerie(BigDecimal tipoSerie) {
		this.tipoSerie = tipoSerie;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}