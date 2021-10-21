package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;


/**
 * The persistent class for the DETALLE_INGRESO database table.
 * 
 */
@Entity
@Table(name="DETALLE_INGRESO")
@NamedQuery(name="DetalleIngreso.findAll", query="SELECT d FROM DetalleIngreso d")
public class DetalleIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETINGRESO_ID_GENERATOR", sequenceName="DETINGRESO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETINGRESO_ID_GENERATOR")
	@Column(name="DETALLE_INGRESO_ID")
	private Long detalleIngresoId;

	private BigDecimal cantidad;
	
	private BigDecimal stock;

	@Column(name="CODIGO_ARTICULO")
	private String codigoArticulo;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_ARTICULO", insertable=false, updatable=false)
	private Articulo articulo;

	@Column(name="INGRESO_ID")
	private BigDecimal ingresoId;

	private BigDecimal precio;

	public DetalleIngreso() {
	}

	public Long getDetalleIngresoId() {
		return this.detalleIngresoId;
	}

	public void setDetalleIngresoId(Long detalleIngresoId) {
		this.detalleIngresoId = detalleIngresoId;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoArticulo() {
		return this.codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public BigDecimal getIngresoId() {
		return this.ingresoId;
	}

	public void setIngresoId(BigDecimal ingresoId) {
		this.ingresoId = ingresoId;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public static Comparator<DetalleIngreso> COMPARE_BY_CODE = new Comparator<DetalleIngreso>() {
		public int compare(DetalleIngreso art1, DetalleIngreso art2) {
			Integer num1 = art1.getCodigoArticulo().substring(0, art1.getCodigoArticulo().indexOf("-")==-1?0:art1.getCodigoArticulo().indexOf("-")).isEmpty()?0:new Integer(art1.getCodigoArticulo().substring(0, art1.getCodigoArticulo().indexOf("-")));
			String str1 = art1.getCodigoArticulo().substring(art1.getCodigoArticulo().indexOf("-")+1);
			
			Integer num2 = art2.getCodigoArticulo().substring(0, art2.getCodigoArticulo().indexOf("-")==-1?0:art2.getCodigoArticulo().indexOf("-")).isEmpty()?0:new Integer(art2.getCodigoArticulo().substring(0, art2.getCodigoArticulo().indexOf("-")));
			String str2 = art2.getCodigoArticulo().substring(art2.getCodigoArticulo().indexOf("-")+1);
			
			int comp1 = str1.compareToIgnoreCase(str2);
			if(comp1 == 0) {
				int comp2 = num1.compareTo(num2);
				return comp2;
			} 
			return comp1;
			
		}
	};
}