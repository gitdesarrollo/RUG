package gt.gob.rgm.inv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

public class Inventario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;	
	private String marca;
	private String tipo;
	private String unidad;
	private Long inicial;
	private Long ingreso;
	private Long salida;
	private Long existencia;
	private String codigoBarras;
	private Integer marcaId;
	private Integer proveedorId;
	private Integer tipoArticuloId;
	private Integer unidadMedidaId;
	private Integer minimoExistencia;
	private Boolean perecedero;
	private BigDecimal valor;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public Long getInicial() {
		return inicial;
	}
	public void setInicial(Long inicial) {
		this.inicial = inicial;
	}
	public Long getIngreso() {
		return ingreso;
	}
	public void setIngreso(Long ingreso) {
		this.ingreso = ingreso;
	}
	public Long getSalida() {
		return salida;
	}
	public void setSalida(Long salida) {
		this.salida = salida;
	}
	public Long getExistencia() {
		return existencia;
	}
	public void setExistencia(Long existencia) {
		this.existencia = existencia;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Integer getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}
	public Integer getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}
	public Integer getTipoArticuloId() {
		return tipoArticuloId;
	}
	public void setTipoArticuloId(Integer tipoArticuloId) {
		this.tipoArticuloId = tipoArticuloId;
	}
	public Integer getUnidadMedidaId() {
		return unidadMedidaId;
	}
	public void setUnidadMedidaId(Integer unidadMedidaId) {
		this.unidadMedidaId = unidadMedidaId;
	}
	public Integer getMinimoExistencia() {
		return minimoExistencia;
	}
	public void setMinimoExistencia(Integer minimoExistencia) {
		this.minimoExistencia = minimoExistencia;
	}
	public Boolean getPerecedero() {
		return perecedero;
	}
	public void setPerecedero(Boolean perecedero) {
		this.perecedero = perecedero;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public static Comparator<Inventario> COMPARE_BY_CODE = new Comparator<Inventario>() {
		public int compare(Inventario art1, Inventario art2) {
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
