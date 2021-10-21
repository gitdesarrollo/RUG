package gt.gob.rgm.inv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p WHERE p.estado = 'A' ORDER BY p.nombre")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROVEEDOR_ID_GENERATOR", sequenceName="PROVEEDOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVEEDOR_ID_GENERATOR")
	private Integer id;

	private String direccion;

	private String estado;

	private String nit;

	private String nombre;

	public Proveedor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
