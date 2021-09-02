package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;


/**
 * The persistent class for the DETALLE_REQUISICION database table.
 * 
 */
@Entity
@Table(name="DETALLE_REQUISICION")
@NamedQuery(name="DetalleRequisicion.findAll", query="SELECT d FROM DetalleRequisicion d")
public class DetalleRequisicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETREQUISION_ID_GENERATOR", sequenceName="DETREQUISION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETREQUISION_ID_GENERATOR")
	@Column(name="DETALLE_REQUISION_ID")
	private Long detalleRequisionId;

	private BigDecimal cantidad;

	@Column(name="CANTIDAD_APROBADA")
	private BigDecimal cantidadAprobada;

	@Column(name="CODIGO_ARTICULO")
	private String codigoArticulo;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_ARTICULO", insertable=false, updatable=false)
	private Articulo articulo;

	@Column(name="REQUISICION_ID")
	private String requisicionId;

	public DetalleRequisicion() {
	}

	public Long getDetalleRequisionId() {
		return this.detalleRequisionId;
	}

	public void setDetalleRequisionId(Long detalleRequisionId) {
		this.detalleRequisionId = detalleRequisionId;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCantidadAprobada() {
		return this.cantidadAprobada;
	}

	public void setCantidadAprobada(BigDecimal cantidadAprobada) {
		this.cantidadAprobada = cantidadAprobada;
	}

	public String getCodigoArticulo() {
		return this.codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getRequisicionId() {
		return this.requisicionId;
	}

	public void setRequisicionId(String requisicionId) {
		this.requisicionId = requisicionId;
	}
	
	public static Comparator<DetalleRequisicion> COMPARE_BY_CODE = new Comparator<DetalleRequisicion>() {
		public int compare(DetalleRequisicion art1, DetalleRequisicion art2) {
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