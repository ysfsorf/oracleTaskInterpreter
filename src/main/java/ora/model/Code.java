package ora.model;

public class Code {

	private String code;
	private String resultat;

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Code() {

	}

	public String getCode() {
		return code;
	}

	public Code(String code, String resultat) {
		super();
		this.code = code;
		this.resultat = resultat;
	}

	public void setCode(String x) {
		this.code = x;
	}
}
