package gt.gob.rgm.inv.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

import javax.persistence.*;


/**
 * The persistent class for the DETALLE_SALIDA database table.
 * 
 */
@Entity
@Table(name="DETALLE_SALIDA")
@NamedQuery(name="DetalleSalida.findAll", query="SELECT d FROM DetalleSalida d")
public class DetalleSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETSALIDA_ID_GENERATOR", sequenceName="DETSALIDA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETSALIDA_ID_GENERATOR")
	@Column(name="DETALLE_SALIDA_ID")
	private Long detalleSalidaId;

	@Column(name="CODIGO_ARTICULO")
	private String codigoArticulo;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_ARTICULO", insertable=false, updatable=false)
	private Articulo articulo;

	@Column(name="SALIDA_ID")
	private String salidaId;
	
	private BigDecimal cantidad;

	public DetalleSalida() {
	}

	public Long getDetalleSalidaId() {
		return this.detalleSalidaId;
	}

	public void setDetalleSalidaId(Long detalleSalidaId) {
		this.detalleSalidaId = detalleSalidaId;
	}

	public String getCodigoArticulo() {
		return this.codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getSalidaId() {
		return this.salidaId;
	}

	public void setSalidaId(String salidaId) {
		this.salidaId = salidaId;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public static Comparator<DetalleSalida> COMPARE_BY_CODE = new Comparator<DetalleSalida>() {
		public int compare(DetalleSalida art1, DetalleSalida art2) {
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