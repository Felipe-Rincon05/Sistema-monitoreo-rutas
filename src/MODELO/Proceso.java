package MODELO;

import java.io.*;
import java.util.Calendar;

import javax.swing.JOptionPane;

import VISTA.InOut;

public class Proceso {
	InOut ob = new InOut ();
	String placas[],codigos[],meses[],dia_semana[],cedula[];
	int c=0, aux1, d=0,aux2,pago,p=0,e=0,z=0,horas[][], aux3 = 0;
	Conductor array[];
	Mecanico mecanicos[];
	Persona Jefe;
	public Proceso () {
		array = new Conductor [2];
		meses = new String [12] ;
		dia_semana = new String [8];
		mecanicos = new Mecanico [2];
		placas = new String [array.length];
		codigos = new String [array.length + mecanicos.length];
		horas = new int [codigos.length][5];
		cedula = new String [codigos.length + 1];
	}
	//EMPLEADO
	public void datos_conductor () {
			int DD1, MM1, AAAA1;
			String nombre, codigo, cedula, estado, Bus,DD,MM,AAAA,auxp,auxz,contrasena, cargo;
			if(z == array.length) {
				ob.mostrarDatos("No es posible ingresar mas empleados");
				Menu_tipo();
			}
			if (p < codigos.length) {
			do {
			nombre = ob.pedirString("Ingrese el nombre");
			} while (!validarString(nombre));
			codigo = codigos[p];
			do {
			cedula = ob.pedirString("Ingrese la cedula");
			} while (!validarcodigo(cedula) || !cedulas_iguales (cedula));
			contrasena = ob.pedirString("Digite la contraseña ");
			cargo = "Conductor";
			ob.mostrarDatos("Digite los datos de la licencia de coducir");
			do {
			DD = JOptionPane.showInputDialog(null, "Digite el dia de expedicion:", "DD");
			DD1 = Integer.parseInt (DD);
			} while (!validarlon2(DD) && !validarcodigo(DD) && DD1 <= 0 || DD1 >= 32);
			do {
			MM = JOptionPane.showInputDialog(null, "Digite el mes de expedicion:", "MM");
			MM1 = Integer.parseInt (MM);
			} while (!validarlon2(MM) && !validarcodigo(MM) && MM1 <= 0 || MM1 >= 13);
			do {
			AAAA = JOptionPane.showInputDialog(null, "Digite el año de expedicion:", "AAAA");
			AAAA1 = Integer.parseInt (AAAA);
			} while (!validarlon4(AAAA) && !validarcodigo(AAAA) && AAAA1 <= 2011 || AAAA1 >= 2023);
			estado = "Inactivo";
			Bus = placas[z];
			Conductor array_aux = new Conductor (nombre, cedula, contrasena, cargo ,codigo, estado,DD,MM,AAAA,Bus);
			array [z] = array_aux;
			p++;
			z++;
			auxp = p+"";
			auxz = z+"";
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("z.txt"))){	
				bw.write(auxz);
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("cedulas.txt",true))){	
				bw.write(cedula);
				bw.newLine();
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("p.txt"))){	
				bw.write(auxp);
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("registro.txt",true))){	
				bw.newLine();
				bw.write(codigo);
				bw.newLine();
				bw.write(contrasena);
				bw.newLine();
				bw.write(nombre);
				bw.newLine();
				bw.write(cedula);
				bw.newLine();
				bw.write(cargo);
				bw.newLine();
				bw.write(estado);
				bw.newLine();
				bw.write(Bus);
				bw.newLine();
				bw.write(DD);
				bw.newLine();
				bw.write(MM);
				bw.newLine();
				bw.write(AAAA);
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
		} else {ob.mostrarDatos("No se pueden ingresar mas empleados");}
		Menu_tipo();
	}
	public void datos_mecanico() {
		String nombre = null, cedula = null, contrasena = null, cargo = null, estado = null,codigo = null,auxp,auxe;
		if(e == mecanicos.length) {
			ob.mostrarDatos("No es posible ingresar mas empleados");
			Menu_tipo();
		}
			do {
				nombre = ob.pedirString("Ingrese el nombre");
			} while (!validarString(nombre));
			do {
				cedula = ob.pedirString("Ingrese la cedula");
			} while (!validarcodigo(cedula) || !cedulas_iguales (cedula));
			codigo = codigos[p];
			contrasena = ob.pedirString("Digite la contraseña ");
			cargo = "Mecanico";
			estado = "Inactivo";
			Mecanico aux = new Mecanico (nombre, cedula, contrasena, cargo, estado,codigo);
			mecanicos[e] = aux;
			p++;
			auxp = p+"";
			e++;
			auxe = e+"";
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("p.txt"))){	
			bw.write(auxp);
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("e.txt"))){	
			bw.write(auxe);
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("cedulas.txt",true))){	
			bw.write(cedula);
			bw.newLine();
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("mecanicos.txt",true))){	
			bw.newLine();
			bw.write(codigo);
			bw.newLine();
			bw.write(nombre);
			bw.newLine();
			bw.write(cedula);
			bw.newLine();
			bw.write(contrasena);
			bw.newLine();
			bw.write(cargo);
			bw.newLine();
			bw.write(estado);
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		Menu_tipo();
	}
	public void modificar_datos () {
		String aux,DD,MM,AAAA;
		int DD1, MM1, AAAA1;
		boolean flag = false;
		aux = ob.pedirString("Digite el codigo del empleado: ");
		for(int i=0;i<array.length;i++) {
			if(aux.equals(array[i].getCodigo())) {
				ob.mostrarDatos("Digite los datos de la licencia de coducir");
				do {
					DD = JOptionPane.showInputDialog(null, "Digite el dia de expedicion:", "DD");
					DD1 = Integer.parseInt (DD);
					} while (!validarlon2(DD) && !validarcodigo(DD) && DD1 <= 0 || DD1 >= 32);
					do {
					MM = JOptionPane.showInputDialog(null, "Digite el mes de expedicion:", "MM");
					MM1 = Integer.parseInt (MM);
					} while (!validarlon2(MM) && !validarcodigo(MM) && MM1 <= 0 || MM1 >= 13);
					do {
					AAAA = JOptionPane.showInputDialog(null, "Digite el año de expedicion:", "AAAA");
					AAAA1 = Integer.parseInt (AAAA);
					} while (!validarlon4(AAAA) && !validarcodigo(AAAA) && AAAA1 <= 2011 || AAAA1 >= 2023);
				array[i].setDD(DD);
				array[i].setMM(MM);
				array[i].setAAAA(AAAA);
				flag = true;
			}
			File bp = new File("registro.txt");
			bp.delete();
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("registro.txt",true))){
				bw.write(array[i].getCodigo());
				bw.newLine();
				bw.write(array[i].getNombre());
				bw.newLine();
				bw.write(array[i].getCedula());
				bw.newLine();
				bw.write(array[i].getEstado());
				bw.newLine();
				bw.write(array[i].getBus());
				bw.newLine();
				bw.write(array[i].getDD());
				bw.newLine();
				bw.write(array[i].getMM());
				bw.newLine();
				bw.write(array[i].getAAAA());
				bw.newLine();
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
		}
		if(!flag) {
			ob.mostrarDatos("El codigo no fue encontrado");
			menu_datos ();
		}
	}
	public void inicio_jornada () {
		String m=null,codigo_j,aux;
		int hora, minutos, n=0,aux3=1;
		do {
		codigo_j = ob.pedirString("Ingrese el codigo del empleado \n");
		} while (!codigoexistente(codigo_j));
		Calendar calendario = Calendar.getInstance();
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		for (n=0; n < array.length; n++) {
			if (codigo_j.equals(array[n].getCodigo()) && !verificacionlleno_inicio (codigo_j, n,aux3)) {
				aux = "Activo";
				array[n].setEstado(aux);
				m = "Los datos del empleado son: \n";
				m += "Codigo: " + array[n].getCodigo() + "\n";
				m += "Nombre: " + array[n].getNombre() + "\n";
				m += "Cedula: " + array[n].getCedula() + "\n";
				m += "Estado: " + array[n].getEstado()+ "\n";
				m += "Placa: " + array[n].getBus() + "\n";
				m += "Licencia: " + array[n].getDD() + "-" + array[n].getMM() + "-"+array[n].getAAAA()+"\n";	
				horas[n][1] = hora;
				horas[n][2] = minutos;
				m += "Hora de ingreso: " + horas[n][1] + ":" + horas[n][2] + "\n";
				hora = 0;
				minutos = 0;
				ob.mostrarDatos(m);
			}
		}
		//
		File bp1 = new File("horas.txt");
		bp1.delete();
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("horas.txt"))){	
			for(int r=0;r<array.length;r++) {
				bw.write(horas[r][0]+"");
				bw.newLine();
				bw.write(horas[r][1]+"");
				bw.newLine();
				bw.write(horas[r][2]+"");
				bw.newLine();
				bw.write(horas[r][3]+"");
				bw.newLine();
				bw.write(horas[r][4]+"");
				bw.newLine();
			}
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		//
		//
		File bp = new File("registro.txt");
		bp.delete();
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("registro.txt",true))){	
			for(int r=0;r<array.length;r++) {
				bw.newLine();
				bw.write(array[r].getCodigo());
				bw.newLine();
				bw.write(array[r].getNombre());
				bw.newLine();
				bw.write(array[r].getCedula());
				bw.newLine();
				bw.write(array[r].getEstado());
				bw.newLine();
				bw.write(array[r].getBus());
				bw.newLine();
				bw.write(array[r].getDD());
				bw.newLine();
				bw.write(array[r].getMM());
				bw.newLine();
				bw.write(array[r].getAAAA());
			}
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		//
	}
	public void fin_jornada () {
		String m=null,codigo_j,aux;
		int hora, minutos,aux3=2;
		Calendar calendario = Calendar.getInstance();
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		do {
		codigo_j = ob.pedirString("Ingrese el codigo del empleado \n");
		} while (!codigoexistente(codigo_j));
		for (int n = 0; n < array.length; n++) {
			if (codigo_j.equals(array[n].getCodigo()) && verificacionlleno_inicio(codigo_j,n,aux3) && !verificacionlleno_fin(codigo_j,n)) {
					aux = "Inactivo";
					array[n].setEstado(aux);
					m = "Los datos del empleado son: \n";
					m += "Codigo: " + array[n].getCodigo() + "\n";
					m += "Nombre: " + array[n].getNombre() + "\n";
					m += "Cedula: " + array[n].getCedula() + "\n";
					m += "Estado: " + array[n].getEstado()+ "\n";
					m += "Placa: " + array[n].getBus() + "\n";
					m += "Licencia: " + array[n].getDD() + "-" + array[n].getMM() + "-"+array[n].getAAAA()+"\n";
					horas[n][3] = hora;
					horas[n][4] = minutos;
					m += "Hora de Salida: " + horas[n][3] + ":" + horas[n][4] + "\n";
					ob.mostrarDatos(m);
					File bp = new File("registro.txt");
					bp.delete();
					try(BufferedWriter bw=new BufferedWriter(new FileWriter ("registro.txt",true))){	
						for(int r=0;r<array.length;r++) {
							bw.newLine();
							bw.write(array[r].getCodigo());
							bw.newLine();
							bw.write(array[r].getNombre());
							bw.newLine();
							bw.write(array[r].getCedula());
							bw.newLine();
							bw.write(array[r].getEstado());
							bw.newLine();
							bw.write(array[r].getBus());
							bw.newLine();
							bw.write(array[r].getDD());
							bw.newLine();
							bw.write(array[r].getMM());
							bw.newLine();
							bw.write(array[r].getAAAA());
						}
					} catch (IOException e) {
						ob.mostrarDatos("Error");
					}
			} 
			//
			if (codigo_j.equals(mecanicos[n].getCodigo()) && verificacionlleno_inicio(codigo_j,n,aux3) && !verificacionlleno_fin(codigo_j,n)) {
				aux = "Inactivo";
				mecanicos[n].setEstado(aux);
				m = "Los datos del empleado son: \n";
				m += "Codigo: " + mecanicos[n].getCodigo() + "\n";
				m += "Nombre: " + mecanicos[n].getNombre() + "\n";
				m += "Cedula: " + mecanicos[n].getCedula() + "\n";
				m += "Estado: " + mecanicos[n].getEstado()+ "\n";
				horas[n][3] = hora;
				horas[n][4] = minutos;
				m += "Hora de Salida: " + horas[n][3] + ":" + horas[n][4] + "\n";
				ob.mostrarDatos(m);
				File bp = new File("mecanicos.txt");
				bp.delete();
				try(BufferedWriter bw=new BufferedWriter(new FileWriter ("registro.txt",true))){	
					for(int r=0;r<mecanicos.length;r++) {
						bw.newLine();
						bw.write(mecanicos[r].getCodigo());
						bw.newLine();
						bw.write(mecanicos[r].getNombre());
						bw.newLine();
						bw.write(mecanicos[r].getCedula());
						bw.newLine();
						bw.write(mecanicos[r].getEstado());
						bw.newLine();
					}
				} catch (IOException e) {
					ob.mostrarDatos("Error");
				}
		} 
			//
		}
		File bp1 = new File("horas.txt");
		bp1.delete();
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("horas.txt"))){	
			for(int r=0;r<codigos.length;r++) {
				bw.write(horas[r][0]+"");
				bw.newLine();
				bw.write(horas[r][1]+"");
				bw.newLine();
				bw.write(horas[r][2]+"");
				bw.newLine();
				bw.write(horas[r][3]+"");
				bw.newLine();
				bw.write(horas[r][4]+"");
				bw.newLine();
			}
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		
	}
	public void consultarcodigo (){
		String busqueda, m = "";
		boolean flag=false;
		int  q = 0, y = 0;
		busqueda = ob.pedirString("Digite el codigo que desea consultar");
		for (int x = 0; x < array.length; x++) {
			if (array[x] == null) { q++; }}
		for (int x = 0; x < mecanicos.length; x++) {
			if (mecanicos[x] == null) { y++; }}
		if (q != 0 && y != 0) {
			ob.mostrarDatos("Para consultar es necesario que todos los empleados se registren.");
			consultar_datos ();
		}
				for (int n = 0; n < array.length; n++) {
					if (busqueda.equals(array[n].getCodigo())) {
						m = "Los datos del empleado son: \n";
						m += "Codigo: " + array[n].getCodigo() + "\n";
						m += "Nombre: " + array[n].getNombre() + "\n";
						m += "Cedula: " + array[n].getCedula() + "\n";
						m += "Estado: " + array[n].getEstado()+ "\n";
						m += "Placa: " + array[n].getBus() + "\n";
						m += "Licencia: " + array[n].getDD() + "-" + array[n].getMM() + "-"+array[n].getAAAA()+"\n";
						flag = true;
					}
					
				}
				for (int n = 0; n < mecanicos.length; n++) {
					if (busqueda.equals(mecanicos[n].getCodigo())) {
						m = "Los datos del empleado son: \n";
						m += "Codigo: " + mecanicos[n].getCodigo() + "\n";
						m += "Nombre: " + mecanicos[n].getNombre() + "\n";
						m += "Cedula: " + mecanicos[n].getCedula() + "\n";
						m += "Estado: " + mecanicos[n].getEstado()+ "\n";
						flag = true;
					}
					
				}
		if (flag) {
		ob.mostrarDatos(m);
		}else if(!flag) {
			ob.mostrarDatos("No se encontro el codigo");
			consultarcodigo();
		}
	}
	public void consultarplaca () {
		String busqueda, m = "";
		int l = 0;
		boolean flag=false;
		for (int x = 0; x < mecanicos.length; x++) {
			if (mecanicos[x] == null) { l++; }}
		if (l != 0) {
			ob.mostrarDatos("Para consultar es necesario que todos los conductores se registren.");
			consultar_datos ();
		}
		busqueda = ob.pedirString("Digite la placa que desea consultar");
		for (int n = 0; n < array.length; n++) {
			if (busqueda.equals(array[n].getBus())) {
				m = "Los datos del empleado son: \n";
				m += "Codigo: " + array[n].getCodigo() + "\n";
				m += "Nombre: " + array[n].getNombre() + "\n";
				m += "Cedula: " + array[n].getCedula() + "\n";
				m += "Estado: " + array[n].getEstado()+ "\n";
				m += "Placa: " + array[n].getBus() + "\n";
				m += "Licencia: " + array[n].getDD() + "-" + array[n].getMM() + "-"+array[n].getAAAA()+"\n";
				flag = true;
			}
		}
		if (flag) {
			ob.mostrarDatos(m);
		}else if(!flag) {
			ob.mostrarDatos("No se encontro la placa");
			consultarplaca();
		}
	}
	//DATOS JEFE
	public void datos_jefe() {
		String nombre = null, cedula = null, contrasena = null, cargo = null,linea;
		do {
			nombre = ob.pedirString("Ingrese el nombre");
		} while (!validarString(nombre));
		do {
			cedula = ob.pedirString("Ingrese la cedula");
		} while (!validarcodigo(cedula) || !cedulas_iguales(cedula));
		try(BufferedReader br=new BufferedReader(new FileReader("contraseña.txt"))){
			while ((linea = br.readLine()) !=null){
				contrasena=linea;
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		cargo = "Jefe";
		Persona aux = new Persona (nombre, cedula, contrasena, cargo);
		Jefe = aux;
		try(BufferedWriter bw=new BufferedWriter(new FileWriter ("cedulas.txt",true))){	
			bw.write(cedula);
			bw.newLine();
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
	try(BufferedWriter bw=new BufferedWriter(new FileWriter ("Jefe.txt"))){	
		bw.newLine();
		bw.write(nombre);
		bw.newLine();
		bw.write(cedula);
		bw.newLine();
		bw.write(contrasena);
		bw.newLine();
		bw.write(cargo);
		bw.newLine();
	} catch (IOException e) {
		ob.mostrarDatos("Error");
	}
	}
	//CAMBIO CONTRASEÑA
	public void cambiocontra () {
		String nueva;
		nueva = ob.pedirString("Digite la nueva contraseña");
		try (FileWriter fw = new FileWriter("Contraseña.txt")) {
			fw.write(nueva);
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		ob.mostrarDatos("La contraseña fue cambiada con exito");
		menu();
	}
	//PLACAS
	public void agregar_placa () {
		String nuevo, letras, num;
		for (int x = 0; x < placas.length; x++) {
			if (placas[x] == null) { c++; }}
		if (c > 0) {
			ob.mostrarDatos("Ingrese las placas");
			for(int i=0;i<placas.length;i++) {
			do {
			nuevo= ob.pedirString("Placa "+(i+1));
			if (nuevo.length() < 6) {
				letras = "000";
				num = "000";
			} else {
				letras = nuevo.substring(0, 3);
				num = nuevo.substring(3, 6);
			}
			}while(!validarplaca(letras) || !validarlon6(nuevo) || !validarcodigo(num) || !validarlon3(num) || !validarlon3(letras));
			placas[i]=nuevo;
			}
			if (placasiguales()) {
			ob.mostrarDatos("Las placas se ingresaron con exito");
			} else {
				ob.mostrarDatos("Ingreso dos o mas placas iguales, vuelva a empezar");
				menu_placas();
			} 
			for(int i=0;i<placas.length;i++) {
				try(BufferedWriter bw=new BufferedWriter(new FileWriter ("placas.txt",true))){
					bw.write(placas[i]);
					bw.newLine();
				} catch (IOException e) {
					ob.mostrarDatos("Error");
				}
			}
			
			aux1 = c;
			c = 0;
		} else {ob.mostrarDatos("No es posible ingresar mas vehiculos");}	
	}
	public void modificar_placa () {
		String mod, letras, num, letras1, num1, aux;
		boolean flag = false;
		int q=0;
		for (int x = 0; x < placas.length; x++) {
			if (placas[x] == null) { q++; }}
		if (q == 0) {
			do {
			mod = ob.pedirString("Digite la placa que desea modificar");
			if (mod.length() < 6) {
				letras = "000";
				num = "000";
			} else {
				letras = mod.substring(0, 3);
				num = mod.substring(3, 6);
			}
			} while(!validarplaca(letras) || !validarlon6(mod) || !validarcodigo(num) || !validarlon3(num) || !validarlon3(letras));
			for(int i=0;i<placas.length;i++) {
				if(mod.equals(placas[i])) {
					do {
						aux = placas[i];
						placas[i] = ob.pedirString("Digite la nueva placa");
						if (!placasiguales()) { 
							ob.mostrarDatos("Ingreso una placa ya existente, vuelva a empezar"); 
							placas[i] = aux;
							menu_placas();
						}
						if (placas[i].length() < 6) {
							letras1 = "000";
							num1 = "000";
						} else {
							letras1 = placas[i].substring(0, 3);
							num1 = placas[i].substring(3, 6);
						}
					} while(!validarplaca(letras1) || !validarlon6(placas[i]) || !validarcodigo(num1) || !validarlon3(num1) || !validarlon3(letras1));
					ob.mostrarDatos("La placa se modifico con exito");
					flag = true;
				}
			}
			//
			File bp = new File("placas.txt");
			bp.delete();
			for(int i=0;i<placas.length;i++) {
				try(BufferedWriter bw=new BufferedWriter(new FileWriter ("placas.txt" ,true))){
					bw.write(placas[i]);
					bw.newLine();
				} catch (IOException e) {
					ob.mostrarDatos("Error");
				}
			}
			//
			if(!flag) {ob.mostrarDatos("La placa que desea modificar no se encontro");}
		} else { 
			ob.mostrarDatos("No hay datos para modificar");
			menu_placas();
		}
	}
	public void mostrar_placa() {
		int q=0;
		for (int x = 0; x < placas.length; x++) {
			if (placas[x] == null) { q++; }}
		if (q > 0) {ob.mostrarDatos("No hay datos para mostrar");
		} else {
			if (!placasiguales()) {ob.mostrarDatos("No hay datos para mostrar");
			} else {
				String m = "Placas ingresadas \n\n";
				for(int i=0;i<placas.length;i++) {
					if(placas[i] != "999999") {
						m += placas[i]+"\n";
					}
				}
				ob.mostrarDatos(m);
			}
		}
	}
	//CODIGOS
	public void agregar_codigos () {
		String nuevo;
		for (int x = 0; x < codigos.length; x++) {
			if (codigos[x] == null) { d++; }}
		if (d > 0) {
		ob.mostrarDatos("Ingrese los codigos");
		for(int i=0;i<codigos.length;i++) {
		do {
		nuevo =	ob.pedirString("Codigo "+(i+1));
		}while(!validarcodigo(nuevo) || !validarlon3(nuevo));
		codigos[i] = nuevo;
		horas[i][0] = Integer.parseInt(nuevo);
		}
		//
		if (codigosiguales()) {
			ob.mostrarDatos("Los codigos se ingresaron con exito");
			} else {
				ob.mostrarDatos("Ingreso dos o mas codigos iguales, vuelva a empezar");
				menu_codigos();
			} 
		for(int i=0;i<codigos.length;i++) {
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("codigos.txt",true))){
				bw.write(codigos[i]);
				bw.newLine();
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
		}
		aux2 = d;
		d = 0;
	} else {ob.mostrarDatos("El ingreso de los empleados ya finalizo");}	
		
	}
	public void modificar_codigos () {	
		String mod, mod2=null;
		boolean flag=false;
		int q=0;
		for (int x = 0; x < codigos.length; x++) {
			if (codigos[x] == null) { q++; }}
		if (q == 0) {
		do {
		mod = ob.pedirString("Digite el codigo que desea modificar");
		}while(!validarcodigo(mod) || !validarlon3(mod));
		for(int i=0;i<codigos.length;i++) {
			if(mod.equals(codigos[i])) {
				do {
					mod2 = codigos[i];
					codigos[i] = ob.pedirString("Digite el nuevo codigo");
					if (!codigosiguales()) { 
						ob.mostrarDatos("Ingreso un codigo ya existente, vuelva a empezar"); 
						codigos[i] = mod2;
						menu_codigos();
					}
				}while(!validarcodigo(mod) || !validarlon3(mod));
				ob.mostrarDatos("El codigo se modifico con exito");
				flag = true;
			}
		}
		//
		File bp = new File("codigos.txt");
		bp.delete();
		for(int i=0;i<codigos.length;i++) {
			try(BufferedWriter bw=new BufferedWriter(new FileWriter ("codigos.txt" ,true))){
				bw.write(codigos[i]);
				bw.newLine();
			} catch (IOException e) {
				ob.mostrarDatos("Error");
			}
		}
		//
		if(!flag) {ob.mostrarDatos("El codigo que desea modificar no se encontro");}
		} else { 
			ob.mostrarDatos("No hay datos para modificar");
			menu_codigos();
		}
	}
	public void mostrar_codigos() {
		int q=0;
		for (int x = 0; x < codigos.length; x++) {
			if (codigos[x] == null) { q++; }}
		if (q > 0) {ob.mostrarDatos("No hay datos para mostrar");
		}else {
		String m = "Codigos ingresados \n\n";
		for(int i=0;i<codigos.length;i++) {
			if(codigos[i] != "999999") {
				m += codigos[i]+"\n";
			}
		}
		ob.mostrarDatos(m);
		}
	}
	//PAGOS
	public void pagos() {
		boolean flag = true;
		do {
			try {
			pago = ob.pedirEntero("Digite el pago por hora que recibiran los empleados");
			} catch(NumberFormatException e) {
				 ob.mostrarDatos("Se han introducido caracteres no numericos");
				 flag = false;
			}
		} while (!flag || pago < 0);
		ob.mostrarDatos("Dato guardado");
	}
	//MENU
	public void Menu_tipo() {
		int op;
		do {
		op = ob.pedirEntero("Digite según la opción \n 1. Conductor \n 2. Mecanico \n 3.Volver \n 4. Salir \n");
		} while (op < 1 || op > 4);
		switch (op) {
		case 1: datos_conductor();
			break;
		case 2: datos_mecanico();
			break;
		case 3: menu_datos ();
			break;
		case 4: menu ();
		break;
		}
	}
	public void consultar_datos () {
		int opc;
		String m;
		do {
			m = "Ingrese el valor correspondiente segun el menu: \n\n";
			m += "1. Consultar con placa\n";
			m += "2. Consultar con codigo\n";
			m += "3. Volver \n";
			m += "4. Salir \n";
			opc = ob.pedirEntero(m);
			
			switch (opc) {
				case 1: consultarplaca ();
						break;
				case 2: consultarcodigo ();
						break;
				case 3: menu_datos ();
						break;
				case 4: menu();
						break;
			}
		}while(opc!=3);
	}
	public void menu_datos () {
		int opc;
		String m;
		do {
			m = "Ingrese el valor correspondiente segun el menu: \n\n";
			m += "1. Agregar datos del empleado\n";
			m += "2. Modificar estado de la licencia\n";
			m += "3. Consultar datos \n";
			m += "4. Volver \n";
			m += "5. Salir \n";
			opc = ob.pedirEntero(m);
			
			switch (opc) {
				case 1: Menu_tipo ();
						break;
				case 2: modificar_datos ();
						break;
				case 3: consultar_datos ();
						break;
				case 4: menu_empleado_prin ();
						break;
				case 5: menu ();
						break;
			}
		}while(opc!=3);
	}
	public void menu_empleado_prin () {
		int opc;
		String m;
		do {
			m = "Ingrese el valor correspondiente segun el menu: \n\n";
			m += "1. Datos de empleados \n";
			m += "2. Registro inicio de jornada \n";
			m += "3. Registro fin de jornada \n";
			m += "4. Volver\n";
			opc = ob.pedirEntero(m);
			
			switch (opc) {
				case 1: menu_datos ();
						break;
				case 2: inicio_jornada ();
						break;
				case 3: fin_jornada ();
						break;
				case 4: menu ();
						break;
			}
		}while(opc!=4);
	}
	public void menu () {
		int opc;
		String m;
		/*
		for(int i=0;i<cedula.length;i++){
			cedula[i] = " ";
		}
		*/
		actulizar ();
		do {
			m="Bienvenido\n";
			m+=calendario()+"\n\n";
			m += "Ingrese el valor correspondiente segun el menu: \n";
			m += "1. Jefe\n";
			m += "2. Empleado\n";

			opc = ob.pedirEntero(m);
			switch (opc) {
				case 1: String contra,linea,contra_jefe="";
						contra = ob.pedirString("Ingrese la contraseña:");
						//
						try(BufferedReader br=new BufferedReader(new FileReader("contraseña.txt"))){
							while ((linea = br.readLine()) !=null){
								contra_jefe=linea;
							}
						} catch (FileNotFoundException e) {
							ob.mostrarDatos("Error");
						} catch (IOException e) {
							ob.mostrarDatos("Error");
						}
						//
						if(contra.equals(contra_jefe)) {
							boolean flag = true;
							try(BufferedReader br=new BufferedReader(new FileReader("Jefe.txt"))){
								linea = br.readLine();
									if(linea == null) {
										flag = false;
									}
							} catch (FileNotFoundException e) {
								ob.mostrarDatos("Error");
							} catch (IOException e) {
								ob.mostrarDatos("Error");
							}
							if (!flag && aux3 == 0) {
								datos_jefe();
								menu_jefe_prin();
							} else {
								menu_jefe_prin();
							}
						}else {
							ob.mostrarDatos("Contraseña incorrecta");
							menu();
						}
					break;
					
				case 2: menu_empleado_prin ();
						break;
			}
		}while(opc>2 || opc<1);
		
	}
	public void menu_placas() {
		int opc;
		String m;
		do {
			m = "Ingrese el valor correspondiente segun el menu: \n\n";
			m += "1. Agregar placas\n";
			m += "2. Modificar placas\n";
			m += "3. Mostrar placas \n";
			m += "4. Volver \n";
			m += "5. Salir \n";
			
			opc = ob.pedirEntero(m);
			
			switch (opc) {
				case 1: agregar_placa();
						break;
				case 2: modificar_placa();
						break;
				case 3: mostrar_placa();
						break;
				case 4: menu_jefe_prin ();
						break;
				case 5: menu();
						break;
			}
		}while(opc!=5);
	}
	public void menu_codigos () {
		int opc;
		String m;
		do {
			m = "Ingrese el valor correspondiente segun el menu: \n\n";
			m += "1. Agregar codigos\n";
			m += "2. Modificar codigos\n";
			m += "3. Mostrar codigos \n";
			m += "4. Volver \n";
			m += "5. Salir \n";
			opc = ob.pedirEntero(m);
			
			switch (opc) {
				case 1: agregar_codigos();
						break;
				case 2: modificar_codigos ();
						break;
				case 3:  mostrar_codigos();
						break;
				case 4: menu_jefe_prin ();
						break;
				case 5: menu();
						break;
			}
		}while(opc!=5);
	}
	public void menu_jefe_prin () {
		int opc;
		String m;
		do {
			m = "Bienvenid@ "+ Jefe.getNombre() + " \n";
			m += "Ingrese el valor correspondiente segun el menu: \n\n";
			m += "1. Placas\n";
			m += "2. Codigos\n";
			m += "3. Pagos\n";
			m += "4. Cambiar contraseña\n";
			m += "5. Consultar empleado\n";
			m += "6. Volver \n";
			opc = ob.pedirEntero(m);
			
			switch (opc) {
				case 1: menu_placas();
						break;
				case 2: menu_codigos ();
						break;
				case 3: pagos();
						break;
				case 4: cambiocontra ();
						break;
				case 5: consultar_datos ();
						break;
				case 6: menu();
						break;
			}
		}while(opc!=4);
	}
	//VALIDACION
	public boolean cedulas_iguales ( String nuevo) {
		try(BufferedReader br=new BufferedReader(new FileReader("cedulas.txt"))){
			String linea;
			int w=0;
			while ((linea = br.readLine()) !=null){
				cedula[w]=linea;
				w++;
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		
		boolean flag = true;
		for(int i=0;i<cedula.length;i++) {
		if (nuevo.equals(cedula[i])){
			flag = false;
			}
		}
		return flag;
	}
	public boolean verificacionlleno_inicio (String codigo_j, int n,int aux3){
		boolean flag = false;
		if(horas[n][1] != 0) {
			if(aux3 == 1) {
				ob.mostrarDatos("El codigo ingresado ya resgistro la hora de entrada");
			}
			flag = true;
		}
		if(flag == false && aux3 == 2) {
			ob.mostrarDatos("Primero registre hora de entrada");
		}
		return flag;
	}
	public boolean verificacionlleno_fin (String codigo_j, int n){
		boolean flag = false;
		if(horas[n][3] != 0) {
			ob.mostrarDatos("El codigo ingresado ya resgistro la hora de fin");
			flag = true;
		}
		return flag;
	}
	public boolean validarplaca(String cadena){
		boolean e=true;
        for (int i = 0; i < cadena.length(); i++) {
                if(cadena.charAt(i)<65 || cadena.charAt(i)>=91){
                 e=false;
             }
       }
        return e;}
	public boolean validarString(String cadena){
        boolean e=true;
         for (int i = 0; i < cadena.length(); i++) {
              if(cadena.charAt(i)!=' ' ){
                 if( cadena.charAt(i)<65 || cadena.charAt(i)>=91 && cadena.charAt(i)<=96 || cadena.charAt(i)>122 && cadena.charAt(i)!='ñ' ){
                  e=false;
              }
         }
        }
         return e;
    }	
	public boolean validarcodigo(String cadena){
	    boolean e=true;
	     for (int i = 0; i < cadena.length(); i++) {
	          
	         if( cadena.charAt(i)<48 || cadena.charAt(i)>57 ){
	         e=false;
	          }
	         }
	     
	     return e;}
	public boolean validarlon3 (String cadena){
		return cadena.length() == 3;}
	public boolean validarlon2 (String cadena) {
		return cadena.length() == 2;}
	public boolean validarlon4 (String cadena) {
		return cadena.length() == 4;}
	public boolean validarlon6 (String cadena){
		return cadena.length() == 6;}
	public boolean placasiguales () {
		boolean flag = true;
			for (int i = 0; i < placas.length; i++) {
				for (int j = i+1; j < placas.length; j++) {
				if (placas[i].equals(placas[j])) {flag = false;}}
			}
		return flag;
	}
	public boolean codigosiguales () {
		boolean flag = true;
		
		for (int i = 0; i < codigos.length; i++) {
			for (int j = i+1; j < codigos.length; j++) {
			if (codigos[i].equals(codigos[j])) {flag = false;}}
		}
		return flag;
	}
	public boolean codigoexistente(String cadena) {
		boolean ban = false;
		for (int l = 0; l < codigos.length; l++) {
			if (cadena.equals(codigos[l])) {
				ban = true;
			}
		}
		if (!ban) {ob.mostrarDatos("El codigo no fue encontrado \n");}
		return ban;
	}
	//HORA Y FECHA
	public String calendario () {
		String str;
			Calendar calendario = Calendar.getInstance();
			int dia , mes, anio,dia_sem;
			meses[0]="enero";
			meses[1]="febrero";
			meses[2]="marzo";
			meses[3]="abril";
			meses[4]="mayo";
			meses[5]="junio";
			meses[6]="julio";
			meses[7]="agosto";
			meses[8]="septiembre";
			meses[9]="octubre";
			meses[10]="noviembre";
			meses[11]="diciembre";
			
			dia_semana[7] = "Sabado";
			dia_semana[1] = "Domingo";
			dia_semana[2] = "Lunes";
			dia_semana[3] = "Martes";
			dia_semana[4] = "Miercoles";
			dia_semana[5] = "Jueves";
			dia_semana[6] = "Viernes";
			
			dia_sem = calendario.get(Calendar.DAY_OF_WEEK);
			anio = calendario.get(Calendar.YEAR);
			mes = calendario.get(Calendar.MONTH);
			dia = calendario.get(Calendar.DAY_OF_MONTH);
			str = dia_semana[dia_sem]+", "+dia + " de "+ meses[mes] + " del " + anio;
			return str;
	}
	//ACTUALIZACION
	public void actulizar () {
		
		try(BufferedReader br=new BufferedReader(new FileReader("cedulas.txt"))){
			String linea;
			int w=0;
			while ((linea = br.readLine()) !=null){
				cedula[w]=linea;
				w++;
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("codigos.txt"))){
			String linea;
			int w=0;
			while ((linea = br.readLine()) !=null){
				codigos[w]=linea;
				horas[w][0] = Integer.parseInt(linea);
				w++;
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		//
		try(BufferedReader br=new BufferedReader(new FileReader("Jefe.txt"))){
			String linea, nombre, cedula, contrasena, cargo;
			linea = br.readLine();
			linea = br.readLine();
			if( linea != null) {
						nombre = linea;
						linea = br.readLine();
						cedula = linea;
						linea = br.readLine();
						contrasena = linea;
						linea = br.readLine();
						cargo = linea;
						linea = br.readLine();
						Persona Jefe1 = new Persona (nombre, cedula, contrasena, cargo);
						Jefe = Jefe1;
						aux3 = 1;
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		//
		try(BufferedReader br=new BufferedReader(new FileReader("p.txt"))){
			String linea;
			while ((linea = br.readLine()) !=null) {
				p = Integer.parseInt(linea);
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("e.txt"))){
			String linea;
			while ((linea = br.readLine()) !=null) {
				e = Integer.parseInt(linea);
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("z.txt"))){
			String linea;
			while ((linea = br.readLine()) !=null) {
				z = Integer.parseInt(linea);
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("registro.txt"))){
			String linea, codigo, nombre, cedula, estado, bus, DD, MM, AAAA,contrasena,cargo;
			linea = br.readLine();
			linea = br.readLine();
			if( linea != null) {
				for(int w=0;w<array.length;w++) {			
						codigo =linea;
						linea = br.readLine();
						contrasena =linea;
						linea = br.readLine();
						nombre = linea;
						linea = br.readLine();
						cedula = linea;
						linea = br.readLine();
						cargo =linea;
						linea = br.readLine();
						estado = linea;
						linea = br.readLine();
						bus = linea;
						linea = br.readLine();
						DD = linea;
						linea = br.readLine();
						MM = linea;
						linea = br.readLine();
						AAAA = linea;
						linea = br.readLine();
						Conductor persona = new Conductor (nombre, cedula,contrasena,cargo, codigo, estado, DD, MM, AAAA, bus);
						array[w] = persona;
						if(linea==null) {
							w = array.length +1;
						}
				}
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("horas.txt"))){
			String linea;
			linea = br.readLine();
			if( linea != null) {
				for(int w=0;w<array.length;w++) {			
						horas[w][0] = Integer.parseInt(linea);
						linea = br.readLine();
						horas[w][1] = Integer.parseInt(linea);
						linea = br.readLine();
						horas[w][2] = Integer.parseInt(linea);
						linea = br.readLine();
						horas[w][3] = Integer.parseInt(linea);
						linea = br.readLine();
						horas[w][4] = Integer.parseInt(linea);
						linea = br.readLine();
						if(linea==null) {
							w = array.length +1;
						}
				}
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("mecanicos.txt"))){
			String linea, nombre, cedula, estado, contrasena,cargo,codigo;
			linea = br.readLine();
			linea = br.readLine();
			if( linea != null) {
				for(int w=0;w<mecanicos.length;w++) {
						codigo = linea;
						linea = br.readLine();
						nombre = linea;
						linea = br.readLine();
						cedula = linea;
						linea = br.readLine();
						contrasena = linea;
						linea = br.readLine();
						cargo = linea;
						linea = br.readLine();
						estado = linea;
						linea = br.readLine();
						Mecanico persona = new Mecanico (nombre, cedula, contrasena, cargo, estado,codigo);
						mecanicos[w] = persona;
						if(linea==null) {
							w = mecanicos.length +1;
						}
				}
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
		try(BufferedReader br=new BufferedReader(new FileReader("placas.txt"))){
			String linea;
			int t=0;
			while ((linea = br.readLine()) !=null){
				placas[t]=linea;
				t++;
			}
		} catch (FileNotFoundException e) {
			ob.mostrarDatos("Error");
		} catch (IOException e) {
			ob.mostrarDatos("Error");
		}
	}
}
