package clases;


public class Cliente {
 
	private int idCliente;
	private String nombre;
	private String direccion;
	private int codigo;

	/**
	 * Constructor con todos las variables para crear Clientes con todos los parametros
	 * @param idCliente, id unico
	 * @param nombre
	 * @param direccion
	 * @param codigo
	 */


	

	public Cliente(int idCliente, String nombre, String direccion, int codigo) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigo = codigo;
	}
	/**
	 * Constructor de un solo parametro para crear pedidos.
	 * @param idCliente, id unico
	 */
	public Cliente(int idCliente) {
		super();
		this.idCliente = idCliente;
	}
	/**
	 * Constructor vacio
	 */
	public Cliente() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", direccion=" + direccion + ", codigo="
				+ codigo + "]";
	}

}
