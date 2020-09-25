package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;

public class DAOCliente implements Operacion{

	private Connection conexion;
	
	public DAOCliente(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public boolean insertar(Object obj) {
		Cliente cliente = (Cliente)obj;
		boolean resultado; 
		try {
			PreparedStatement st = conexion
					.prepareStatement("INSERT INTO CLIENTES (id, nombre, direccion, telefono) VALUES (?,?,?,?)");
			st.setInt(1, cliente.getId());
			st.setString(2, cliente.getNombre());
			st.setString(3, cliente.getDireccion());
			st.setInt(4, cliente.getTelefono());
			st.execute();
			resultado=true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return resultado;
	}

	@Override
	public boolean modificar(Object obj) {
	Cliente cliente =(Cliente)obj;
	boolean resultado=false;
		try {
			PreparedStatement st = conexion
					.prepareStatement("UPDATE CLIENTES SET nombre=?, direccion=?, telefono=? WHERE id=?");
			st.setInt(4, cliente.getId());
			st.setString(1, cliente.getNombre());
			st.setString(2, cliente.getDireccion());
			st.setInt(3, cliente.getTelefono());
			if (st.execute() == false) {
				resultado=true;
			}
		} catch (SQLException e) {
			System.err.println("Error al modificar cliente.\n" + e.getMessage());
		}
		return resultado;
	}

	@Override
	public boolean eliminar(Object obj) {
		Cliente cliente = (Cliente)obj;
		boolean resultado=false;
		try {
			PreparedStatement st = conexion.prepareStatement("DELETE FROM CLIENTES WHERE id=?");
			st.setInt(1, cliente.getId());
			st.execute();
			if (st.execute() == false) {
				resultado=true;
			}
		} catch (SQLException e) {
			System.err.println("Error al eliminar cliente" + e.getMessage());
		}
		return resultado;
	}

	@Override
	public ArrayList<Object[]> consulta() {
		ResultSet resultado = null;
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		try {
			PreparedStatement st = conexion.prepareStatement("SELECT * FROM CLIENTES");
			resultado = st.executeQuery();
			while (resultado.next()) {
				Object [] object  = new Object[4];
				for (int i = 0; i < 4; i++) {
					object[i]=resultado.getObject(i+1);
				}
				data.add(object);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			data=null;
		}
		return data;
	}
	
	public void recargarClientes(DefaultTableModel modelo) {
		ResultSet resultado = null;
		try {
			PreparedStatement st = conexion.prepareStatement("SELECT * FROM CLIENTES");
			resultado = st.executeQuery();
			while (resultado.next()) {
				String[] registros = new String[4];
				registros[0] = Integer.toString(resultado.getInt("id"));
				registros[1] = resultado.getString("nombre");
				registros[2] = resultado.getString("direccion");
				registros[3] = Integer.toString(resultado.getInt("telefono"));

				modelo.addRow(registros);
			}
		} catch (SQLException e) {
			System.err.println("Falla al mostrar la Base de Datos.\n" + e.getMessage());
		}
	}
	
}
