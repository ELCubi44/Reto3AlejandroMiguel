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
	 * Constructor con todos los parametros para crear pedidos
	 * 
	 * @param cliente
	 * @param precioTotal
	 * @param direccionEnvio
	 * @param fecha
	 */
	public Pedido(Cliente cliente, double precioTotal, String direccionEnvio, Date fecha) {
		super();
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
