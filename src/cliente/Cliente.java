package cliente;

public class Cliente {
	private int idCliente;
	private String nombreCliente;
	private String direccionCliente;
	private int telefonoCliente;
	
	public Cliente(int id, String nombreCliente, String direccionCliente, int telefonoCliente) {
		this.idCliente = id;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.telefonoCliente = telefonoCliente;
	}

	public int getId() {
		return idCliente;
	}

	public String getNombre() {
		return nombreCliente;
	}

	public String getDireccion() {
		return direccionCliente;
	}

	public int getTelefono() {
		return telefonoCliente;
	}
	
}
