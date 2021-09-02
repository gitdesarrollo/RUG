package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;


/**
 * The persistent class for the articulo database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Articulo.findAll", query="SELECT a FROM Articulo a"),
	@NamedQuery(name="Articulo.countAll", query="SELECT COUNT(a) FROM Articulo a")
})
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	private String codigo;
	
	@Transient
	private String codeStr;
	
	@Transient
	private Integer codeNum;

	@Column(name="codigo_barras")
	private String codigoBarras;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="minimo_existencia")
	private Integer minimoExistencia;

	private Boolean perecedero;

	private BigDecimal valor;
	
	private Long correlativo;
	
	private Long stock;
	
	private String estado;

	@Column(name="marca_id")
	private Long marcaId;
	
	@ManyToOne
	@JoinColumn(name="marca_id", insertable=false, updatable=false)
	private Marca marca;

	@Column(name="proveedor_id")
	private Long proveedorId;

	@ManyToOne
	@JoinColumn(name="proveedor_id", insertable=false, updatable=false)
	private Proveedor proveedor;
	
	@Column(name="tipo_id")
	private Long tipoArticuloId;
	
	@ManyToOne
	@JoinColumn(name="tipo_id", insertable=false, updatable=false)
	private TipoArticulo tipoArticulo;

	@Column(name="unidad_id")
	private Long unidadMedidaId;
	
	@ManyToOne
	@JoinColumn(name="unidad_id", insertable=false, updatable=false)
	private UnidadMedida unidadMedida;

	public Articulo() {
	}

	public Articulo(String codigo, String codeStr, Integer codeNum, String codigoBarras, String descripcion,
			Date fechaVencimiento, Integer minimoExistencia, Boolean perecedero, BigDecimal valor, Long correlativo,
			Long stock, String estado, Long marcaId, Marca marca, Long proveedorId, Proveedor proveedor,
			Long tipoArticuloId, TipoArticulo tipoArticulo, Long unidadMedidaId, UnidadMedida unidadMedida) {
		super();
		this.codigo = codigo;
		this.codeStr = codeStr;
		if(codeNum==null)
			this.codeNum = new Integer(0);
		else 
			this.codeNum = new Integer(codeNum);
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.fechaVencimiento = fechaVencimiento;
		this.minimoExistencia = minimoExistencia;
		this.perecedero = perecedero;
		this.valor = valor;
		this.correlativo = correlativo;
		this.stock = stock;
		this.estado = estado;
		this.marcaId = marcaId;
		this.marca = marca;
		this.proveedorId = proveedorId;
		this.proveedor = proveedor;
		this.tipoArticuloId = tipoArticuloId;
		this.tipoArticulo = tipoArticulo;
		this.unidadMedidaId = unidadMedidaId;
		this.unidadMedida = unidadMedida;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getMinimoExistencia() {
		return this.minimoExistencia;
	}

	public void setMinimoExistencia(Integer minimoExistencia) {
		this.minimoExistencia = minimoExistencia;
	}

	public Boolean getPerecedero() {
		return this.perecedero;
	}

	public void setPerecedero(Boolean perecedero) {
		this.perecedero = perecedero;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Long correlativo) {
		this.correlativo = correlativo;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Long getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Long getTipoArticuloId() {
		return tipoArticuloId;
	}

	public void setTipoArticuloId(Long tipoArticuloId) {
		this.tipoArticuloId = tipoArticuloId;
	}

	public TipoArticulo getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(TipoArticulo tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}

	public Long getUnidadMedidaId() {
		return unidadMedidaId;
	}

	public void setUnidadMedidaId(Long unidadMedidaId) {
		this.unidadMedidaId = unidadMedidaId;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getCodeStr() {
		return codeStr;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}

	public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}
	
	public static Comparator<Articulo> COMPARE_BY_CODE = new Comparator<Articulo>() {
		public int compare(Articulo art1, Articulo art2) {
			Integer num1 = art1.getCodigo().substring(0, art1.getCodigo().indexOf("-")==-1?0:art1.getCodigo().indexOf("-")).isEmpty()?0:new Integer(art1.getCodigo().substring(0, art1.getCodigo().indexOf("-")));
			String str1 = art1.getCodigo().substring(art1.getCodigo().indexOf("-")+1);
			
			Integer num2 = art2.getCodigo().substring(0, art2.getCodigo().indexOf("-")==-1?0:art2.getCodigo().indexOf("-")).isEmpty()?0:new Integer(art2.getCodigo().substring(0, art2.getCodigo().indexOf("-")));
			String str2 = art2.getCodigo().substring(art2.getCodigo().indexOf("-")+1);
			
			int comp1 = str1.compareToIgnoreCase(str2);
			if(comp1 == 0) {
				int comp2 = num1.compareTo(num2);
				return comp2;
			} 
			return comp1;
			
		}
	};
	
}