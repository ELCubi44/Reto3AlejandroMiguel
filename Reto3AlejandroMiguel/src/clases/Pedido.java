package clases;

import java.util.Date;

/**
 * @author Alejandro y Miguel
 * @since 14/05/2025
 * @version 1
 */

public class Pedido {

	private int idPedido;
	private Cliente cliente;
	private double precioTotal;
	private String direccionEnvio;
	private Date fecha;
 
	/**
	 * Constructor con 4 parametros para crear pedidos
	 * 
	 * @param cliente        Cliente que realiza el pedido
	 * @param precioTotal    Precio total del pedido
	 * @param direccionEnvio Direcci�n a la que se realiza el pedido
	 * @param fecha          Fecha en la que se realiza el pedido
	 */
	
	public Pedido(Cliente cliente, double precioTotal, String direccionEnvio, Date fecha) {
		super();
		this.cliente = cliente;
		this.precioTotal = precioTotal;
		this.direccionEnvio = direccionEnvio;
		this.fecha = fecha;
	}

	/**
	 * Constrictor con 3 parametros
	 * 
	 * @param precioTotal    Precio total del pedido
	 * @param direccionEnvio Direcci�n a la que se realiza el pedido
	 * @param fecha          Fecha en la que se realiza el pedido
	 */
	public Pedido(double precioTotal, String direccionEnvio, Date fecha) {
		super();
		this.precioTotal = precioTotal;
		this.direccionEnvio = direccionEnvio;
		this.fecha = fecha;
	}

	/**
	 * Constructor con todos los parametros
	 * 
	 * @param idPedido       ID unico del pedido
	 * @param cliente        Cliente que realiza el pedido
	 * @param precioTotal    Precio total del pedido
	 * @param direccionEnvio Direcci�n a la que se realiza el pedido
	 * @param fecha          Fecha en la que se realiza el pedido
	 */
	public Pedido(int idPedido, Cliente cliente, double precioTotal, String direccionEnvio, Date fecha) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.precioTotal = precioTotal;
		this.direccionEnvio = direccionEnvio;
		this.fecha = fecha;
	}

	/**
	 * Constructor vacio
	 */
	public Pedido() {
		super();
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "pedido [idPedido=" + idPedido + ", cliente=" + cliente + ", precioTotal=" + precioTotal
				+ ", direccionEnvio=" + direccionEnvio + ", fecha=" + fecha + "]";
	}

}
