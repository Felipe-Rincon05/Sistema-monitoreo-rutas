package MODELO;

public class Persona {
	
	private String nombre;
	private String cedula;
	private String contrasena;
	private String cargo;
	
	public Persona(String nombre, String cedula, String contrasena, String cargo) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.contrasena = contrasena;
		this.cargo = cargo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}
	public String getContrasena() {
		return contrasena;
	}
	public String getCargo() {
		return cargo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
