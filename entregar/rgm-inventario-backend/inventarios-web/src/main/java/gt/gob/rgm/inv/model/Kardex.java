package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the KARDEX database table.
 * 
 */
@Entity
@NamedQuery(name="Kardex.findAll", query="SELECT k FROM Kardex k")
public class Kardex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="KARDEX_ID_GENERATOR", sequenceName="KARDEX_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KARDEX_ID_GENERATOR")
	@Column(name="KARDEX_ID")
	private long kardexId;

	@Column(name="CODIGO_ARTICULO")
	private String codigoArticulo;

	private BigDecimal entrada;

	private BigDecimal existencia;

	private Timestamp fecha;

	private String referencia;

	private BigDecimal salida;

	public Kardex() {
	}

	public long getKardexId() {
		return this.kardexId;
	}

	public void setKardexId(long kardexId) {
		this.kardexId = kardexId;
	}

	public String getCodigoArticulo() {
		return this.codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public BigDecimal getEntrada() {
		return this.entrada;
	}

	public void setEntrada(BigDecimal entrada) {
		this.entrada = entrada;
	}

	public BigDecimal getExistencia() {
		return this.existencia;
	}

	public void setExistencia(BigDecimal existencia) {
		this.existencia = existencia;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getSalida() {
		return this.salida;
	}

	public void setSalida(BigDecimal salida) {
		this.salida = salida;
	}

}