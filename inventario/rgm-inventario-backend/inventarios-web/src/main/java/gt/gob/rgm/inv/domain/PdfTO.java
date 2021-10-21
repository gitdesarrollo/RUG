package gt.gob.rgm.inv.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PdfTO {

	public String title;
	public String subtitle;
	public Integer rows;
	public Map<String, String> params;
	public List<String> headers;
	public List values;
	public Set<Integer> sections;
	
	public String getTitle() {
		return title; 
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List getValues() {
		return values;
	}
	public void setValues(List values) {
		this.values = values;
	}
	public Set<Integer> getSections() {
		return sections;
	}
	public void setSections(Set<Integer> sections) {
		this.sections = sections;
	}
	
}
