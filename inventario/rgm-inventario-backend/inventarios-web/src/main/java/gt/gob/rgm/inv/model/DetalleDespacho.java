package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;


/**
 * The persistent class for the DETALLE_DESPACHO database table.
 * 
 */
@Entity
@Table(name="DETALLE_DESPACHO")
@NamedQuery(name="DetalleDespacho.findAll", query="SELECT d FROM DetalleDespacho d")
public class DetalleDespacho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETDESPACHO_ID_GENERATOR", sequenceName="DETDESPACHO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETDESPACHO_ID_GENERATOR")
	@Column(name="DETALLE_DESPACHO_ID")
	private Long detalleDespachoId;

	private BigDecimal cantidad;

	@Column(name="CODIGO_ARTICULO")
	private String codigoArticulo;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_ARTICULO", insertable=false, updatable=false)
	private Articulo articulo;

	@Column(name="DESPACHO_ID")
	private BigDecimal despachoId;

	public DetalleDespacho() {
	}

	public Long getDetalleDespachoId() {
		return this.detalleDespachoId;
	}

	public void setDetalleDespachoId(Long detalleDespachoId) {
		this.detalleDespachoId = detalleDespachoId;
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

	public BigDecimal getDespachoId() {
		return this.despachoId;
	}

	public void setDespachoId(BigDecimal despachoId) {
		this.despachoId = despachoId;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public static Comparator<DetalleDespacho> COMPARE_BY_CODE = new Comparator<DetalleDespacho>() {
		public int compare(DetalleDespacho art1, DetalleDespacho art2) {
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