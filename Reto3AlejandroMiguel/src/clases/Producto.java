package clases;

/**
 * @author Alejandro y Miguel
 * @since 14/05/2025
 * @version 1
 */

public class Producto {

	private int idProducto;
	private Categoria categoria;
	private String nombre;
	private double precio;
	private String descripcion;
	private String color;
	private String talla;
	private int stock;

	/**
	 * Constructor con todos los parametros para crear un producto
	 * 
	 * @param idProducto
	 * @param categoria
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param color
	 * @param talla
	 * @param stock
	 */
	public Producto(int idProducto, Categoria categoria, String nombre, double precio, String descripcion, String color,
			String talla, int stock) {
		super();
		this.idProducto = idProducto;
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}

	/**
	 * Constructor que usamos para lista producto en productoDao sin categoria
	 * 
	 * @param idProducto
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param color
	 * @param talla
	 * @param stock
	 */
	public Producto(int idProducto, String nombre, double precio, String descripcion, String color, String talla,
			int stock) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}

	/**
	 * Constructor que usamos para bajar el stock de un producto en ProductoDao
	 * 
	 * @param idProducto
	 * @param stock
	 */
	public Producto(int idProducto, int stock) {
		super();
		this.idProducto = idProducto;
		this.stock = stock;
	}

	/**
	 * 
	 * @param categoria
	 * @param nombre
	 * @param stock
	 */
	public Producto(Categoria categoria, String nombre, int stock) {
		super();
		this.categoria = categoria;
		this.nombre = nombre;
		this.stock = stock;
	}

	/**
	 * Constructor vacio
	 */
	public Producto() {
		super();
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTalla() {
		return talla;
	}

	public void setTall(String talla) {
		this.talla = talla;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", categoria=" + categoria + ", nombre=" + nombre + ", precio="
				+ precio + ", descripcion=" + descripcion + ", color=" + color + ", tall=" + talla + ", stock=" + stock
				+ "]";
	}

}
