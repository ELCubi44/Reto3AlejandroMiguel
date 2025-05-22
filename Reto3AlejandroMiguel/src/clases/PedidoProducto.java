package clases;

/**
 * @author Alejandro y Miguel
 * @since 14/05/2025
 * @version 1
 */

public class PedidoProducto {

	private int idPedidoProducto;
	private Pedido pedido;
	private Producto producto;
	private int unidades;
	private double precio;
 
	/**
	 * Constructor con todos los parametros para crear PedidoProducto
	 * 
	 * @param idPedidoProducto
	 * @param pedido
	 * @param producto
	 * @param unidades
	 * @param precio
	 */
	public PedidoProducto(Pedido pedido, Producto producto, int unidades, double precio) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.unidades = unidades;
		this.precio = precio;
	}
	
	public PedidoProducto(Producto producto, int unidades, double precio) {
		super();
		this.producto = producto;
		this.unidades = unidades;
		this.precio = precio;
	}



	public PedidoProducto(int idPedidoProducto, Producto producto, int unidades, double precio) {
		super();
		this.idPedidoProducto = idPedidoProducto;
		this.producto = producto;
		this.unidades = unidades;
		this.precio = precio;
	}



	/**
	 * Constructor para usar en producto mas vendido
	 * @param producto
	 * @param unidades
	 */
	public PedidoProducto(Producto producto, int unidades) {
		super();
		this.producto = producto;
		this.unidades = unidades;
	}


	/**
	 * 
	 * @param pedido
	 * @param producto
	 * @param unidades
	 */
	public PedidoProducto(Pedido pedido, Producto producto, int unidades) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.unidades = unidades;
	}

	/**
	 * 
	 * @param idPedidoProducto
	 * @param pedido
	 * @param producto
	 * @param unidades
	 * @param precio
	 */
	public PedidoProducto(int idPedidoProducto, Pedido pedido, Producto producto, int unidades, double precio) {
		super();
		this.idPedidoProducto = idPedidoProducto;
		this.pedido = pedido;
		this.producto = producto;
		this.unidades = unidades;
		this.precio = precio;
	}

	/**
	 * Constructor vacio
	 */
	public PedidoProducto() {
		super();
	}

	public int getIdPedidoProducto() {
		return idPedidoProducto;
	}

	public void setIdPedidoProducto(int idPedidoProducto) {
		this.idPedidoProducto = idPedidoProducto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "PedidoProducto [idPedidoProducto=" + idPedidoProducto + ", pedido=" + pedido + ", producto=" + producto
				+ ", unidades=" + unidades + ", precio=" + precio + "]";
	}

}
