package main;
import cliente.Cliente;
import conexion.Conexion;
import graficos.Ventana;

public class Main {
//	public static void main(String[] args) {
//		Conexion c = new Conexion();
//		/* Conexion con la base de datos */
//		c.conectar();
//		
//		/* Agrego entradas a la tabla */
//		c.agregarCliente(new Cliente(1234,"Jacinto Perez SA","Ing. Albertense 121",44441231));
//		c.agregarCliente(new Cliente(2233,"Apple","Av. Libertador 4352",643836343));
//		c.agregarCliente(new Cliente(3344,"Google Corp","the Truth of what it is 12",6547382));
//		c.agregarCliente(new Cliente(4455,"Wayne Enterprise","Av. Cordoba 10101",3214532));
//
//		/* Modifico cliente */
//		c.modificarClientes(new Cliente(1234,"Tesla Motors","Av. Elon Mosca 1212",55555575));
//		
//		/* Elimino cliente */
//		c.eliminarCliente(new Cliente(1234,"","",0));
//		
//		/* Muestro los datos que hay en la tabla */
//		c.mostrarClientes();
//		
//		/* Cierro la base de datos */
//		c.cerrar();
//	}
	
	public static void main(String[] args) {
		Conexion c = new Conexion();
		
//		/* Conexion con la base de datos */
		c.conectar();
		
		Ventana ventana = new Ventana(c);
		ventana.setVisible(true);
		
		c.eliminarCliente(new Cliente(5555,"","",0));
		//c.agregarCliente(new Cliente(5555,"Manaos SA","Rio de la Plata 333",87654398));
	}
}
