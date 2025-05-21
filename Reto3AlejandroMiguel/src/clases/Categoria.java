package clases;

/**
 * @author Alejandro y Miguel
 * @since 14/05/2025
 * @version 1
 */

public class Categoria {

	private int idCategoria;
	private String nombre;
	/**
	* Constructor con todos los parametros para crear categorias
 	* @param idCategoria parametro que almacena el id de la categoria propio
 	* @param nombre que define la categoria
 	*/
	public Categoria(int idCategoria, String nombre) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}
	/**
	* Constructor de un parametro usado en productoDao para listarlo trayendo solo el id que nos proporciona la base de datos
 	* @param idCategoria parametro que almacena el id de la categoria propio
 	*/
	public Categoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}
	/**
	* Constructor vacio
 	*/
	public Categoria() {
		super();
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombre=" + nombre + "]";
	}

}
