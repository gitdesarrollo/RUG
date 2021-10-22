package gt.gob.rgm.inv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="PARAMETRO_CONF")
@NamedQuery(name="ParametroConf.findAll", query="SELECT r FROM ParametroConf r")
public class ParametroConf implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CVE_PARAMETRO")
	private String cveParametro;

	@Lob
	@Column(name="VALOR_PARAMETRO")
	private String valorParametro;

	public ParametroConf() {
		super();
	}

	public String getCveParametro() {
		return cveParametro;
	}

	public void setCveParametro(String cveParametro) {
		this.cveParametro = cveParametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	
}
