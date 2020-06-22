package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cliente.Cliente;

public class Conexion {
	Connection conexion = null;
	
	public void conectar() {
		
		try {
			String url = "clientes.db";
			conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
			if(conexion != null) {
				System.out.println("Conectado");
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
	
	public void agregarCliente(Cliente cliente) {
		try {
			PreparedStatement st = conexion.prepareStatement("INSERT INTO CLIENTES (id, nombre, direccion, telefono) VALUES (?,?,?,?)");
			st.setInt(1, cliente.getId());
			st.setString(2, cliente.getNombre());
			st.setString(3, cliente.getDireccion());
			st.setInt(4, cliente.getTelefono());
			st.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void mostrarClientes() {
		ResultSet resultado = null;
		try {
			PreparedStatement st = conexion.prepareStatement("SELECT * FROM CLIENTES");
			resultado = st.executeQuery();
			while (resultado.next()) {
                System.out.print("ID: ");
                System.out.println(resultado.getInt("id"));

                System.out.print("Nombre: ");
                System.out.println(resultado.getString("nombre"));

                System.out.print("Dirección ");
                System.out.println(resultado.getString("direccion"));

                System.out.print("Telefono ");
                System.out.println(resultado.getString("telefono"));
                
                System.out.println("=======================");
            }
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void modificarClientes(Cliente cliente) {
		try {
			PreparedStatement st = conexion.prepareStatement("UPDATE CLIENTES SET nombre=?, direccion=?, telefono=? WHERE id=?");
			st.setInt(4, cliente.getId());
			st.setString(1, cliente.getNombre());
			st.setString(2, cliente.getDireccion());
			st.setInt(3, cliente.getTelefono());
			st.execute();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	
}
