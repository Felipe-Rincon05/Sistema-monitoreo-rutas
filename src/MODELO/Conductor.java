package MODELO;

public class Conductor extends Persona{
	
	private String estado;
	private String codigo;
	private String DD;
	private String MM;
	private String AAAA;
	private String Bus;
	public Conductor(String nombre, String cedula, String contrasena, String cargo,String codigo,String estado,String DD,String MM,String AAAA,String Bus) {
		super(nombre, cedula, contrasena, cargo);
		this.codigo = codigo;
		this.estado = estado;
		this.DD = DD;
		this.MM = MM;
		this.AAAA = AAAA;
		this.Bus = Bus;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDD() {
		return DD;
	}
	public void setDD(String dD) {
		DD = dD;
	}
	public String getMM() {
		return MM;
	}
	public void setMM(String mM) {
		MM = mM;
	}
	public String getAAAA() {
		return AAAA;
	}
	public void setAAAA(String aAAA) {
		AAAA = aAAA;
	}
	public String getBus() {
		return Bus;
	}
	
}
