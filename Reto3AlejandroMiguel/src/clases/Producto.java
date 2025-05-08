package clases;

public class Producto {

	private int idProducto;
	private Categoria categoria;
	private String nombre;
	private double precio;
	private String descripcion;
	private String color;
	private char tall;
	private int stock;
	
	public Producto(int idProducto, Categoria categoria, String nombre, double precio, String descripcion, String color,
			char tall, int stock) {
		super();
		this.idProducto = idProducto;
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.tall = tall;
		this.stock = stock;
	}

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

	public char getTall() {
		return tall;
	}

	public void setTall(char tall) {
		this.tall = tall;
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
				+ precio + ", descripcion=" + descripcion + ", color=" + color + ", tall=" + tall + ", stock=" + stock
				+ "]";
	}
	
}
