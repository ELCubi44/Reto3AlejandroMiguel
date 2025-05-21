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
	 * @param idProducto  ID unico del producto
	 * @param categoria   Categoria del producto
	 * @param nombre      Nombre del producto
	 * @param precio      Precio del producto
	 * @param descripcion Descripcion breve del producto
	 * @param color       Color del producto
	 * @param talla       Talla del producto
	 * @param stock       Stock del producto
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

	public Producto(int idProducto, Categoria categoria, String nombre) {
		super();
		this.idProducto = idProducto;
		this.categoria = categoria;
		this.nombre = nombre;
	}
	
	/**
	 * Constructor que usamos para lista producto en productoDao sin categoria
	 * 
	 * @param idProducto  ID unico del producto
	 * @param nombre      Nombre del producto
	 * @param precio      Precio del producto
	 * @param descripcion Descripcion breve del producto
	 * @param color       Color del producto
	 * @param talla       Talla del producto
	 * @param stock       Stock del producto
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
	 * 
	 * @param idProducto
	 * @param nombre
	 * @param precio
	 * @param descripcion
	 * @param color
	 * @param talla
	 * @param stock
	 */
	public Producto(Categoria categoria, String nombre, double precio, String descripcion, String color, String talla,
			int stock) {
		super();
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}

	
	
	public Producto(String nombre, double precio, String color, String talla, int stock) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}

	/**
	 * Constructor que usamos para bajar el stock de un producto en ProductoDao
	 * 
	 * @param idProducto ID unico del producto
	 * @param stock      Stock del producto
	 */
	public Producto(int idProducto, int stock) {
		super();
		this.idProducto = idProducto;
		this.stock = stock;
	}
 
	/**
	 * Constructor de 3 parametros
	 * 
	 * @param categoria Categoria del producto
	 * @param nombre    Nombre del producto
	 * @param stock     Stock del producto
	 */
	public Producto(Categoria categoria, String nombre, int stock) {
		super();
		this.categoria = categoria;
		this.nombre = nombre;
		this.stock = stock;
	}
	/**
	 * 
	 * @param categoria
	 * @param nombre
	 */
	public Producto(Categoria categoria, String nombre) {
		super();
		this.categoria = categoria;
		this.nombre = nombre;
	}

	/**
	 * Constructor para usar en top ventas
	 * 
	 * @param idProducto
	 */
	public Producto(int idProducto) {
		super();
		this.idProducto = idProducto;
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
