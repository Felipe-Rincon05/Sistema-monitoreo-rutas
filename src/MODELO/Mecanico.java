package MODELO;

public class Mecanico extends Persona {
	private String estado;
	private String codigo;
	public Mecanico(String nombre, String cedula, String contrasena, String cargo,String estado,String codigo) {
		super(nombre, cedula,contrasena, cargo);
		this.estado = estado;
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigo() {
		return codigo;
	}
	
}