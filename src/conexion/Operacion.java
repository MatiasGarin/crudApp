package conexion;

import java.util.ArrayList;

public interface Operacion {
	
	public boolean insertar(Object obj);
	public boolean modificar(Object obj);
	public boolean eliminar(Object obj);
	public ArrayList<Object[]> consulta();
	
}
