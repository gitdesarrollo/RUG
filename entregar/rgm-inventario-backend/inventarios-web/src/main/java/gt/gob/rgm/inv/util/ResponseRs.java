package gt.gob.rgm.inv.util;

public class ResponseRs {
	
	private Long code;
	private String reason;
	private Object value;
	private Integer total;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "ResponseRs [code=" + code + ", reason=" + reason + ", value=" + value + "]";
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

}
