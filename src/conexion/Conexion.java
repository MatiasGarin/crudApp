package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Conexion {
	private Connection conexion;
	
	public Conexion() {
		conexion = null;
	}
	
	public void conectar() {
		
		try {
			String url = "clientes.db";
			conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
			if(conexion != null) {
				JOptionPane.showMessageDialog(null, "Conectado", "Base de Datos", 1);
			}
		} catch (SQLException e) {
			System.err.println("No se ha podido Conectar " + e.getMessage());
		}
		
	}
	
	public void cerrar() {
		try {
			conexion.close();
			System.out.println("La conexion se cerró correctamente");
		} catch (SQLException e) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public Connection getConexion() {
		return conexion;
	}
	
}
