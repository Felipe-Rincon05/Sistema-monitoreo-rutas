package VISTA;

import javax.swing.JOptionPane;



public class InOut {
 public String pedirString (String m) {
	 return JOptionPane.showInputDialog (m); // capturando datos y siempre lo captura como un String
 }
 
 public int pedirEntero (String m) {
	 //String d = JOptionPane.showInputDialog (m);
	 //int dato = Integer.parseInt (d); // convierte string a entero 
	 return Integer.parseInt (JOptionPane.showInputDialog (m));
 } 
 
 public void mostrarDatos (String m) {
	 JOptionPane.showMessageDialog (null,m); // cout<<
 }
}