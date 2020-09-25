package main;
import conexion.Conexion;
import graficos.Ventana;

public class Main {

	public static void main(String[] args) {
		Conexion c = new Conexion();
		
		/* Conexion con la base de datos */
		c.conectar();
		
		Ventana ventana = new Ventana(c);
		ventana.setVisible(true);
	}
}
