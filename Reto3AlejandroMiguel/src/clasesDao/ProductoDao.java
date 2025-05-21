package clasesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alejandro y Miguel
 * @since 14/05/2025
 * @version 1
 */
import java.util.ArrayList;
import java.util.List;

import clases.Categoria;
import clases.Producto;
import util.Conexion;

public class ProductoDao {

	/**
	 * Metodo para listar todos los productos de determinada categoria
	 * 
	 * @param idCat, nos pasan la categoria que queremos listar sus productos
	 * @return una lista con los productos
	 */
	public static List<Producto> listaCategoria(int idCat) {

		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("select * \r\n" + "from productos a \r\n"
					+ "inner join categorias b on b.idcategoria=a.idcategoria \r\n" + "where b.idcategoria=?;");
			pst.setInt(1, idCat);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				productos.add(new Producto(rs.getInt("idproducto"), new Categoria(rs.getInt("idcategoria")),
						rs.getString("nombre"), rs.getDouble("precio"), rs.getString("Descripcion"),
						rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}

	/**
	 * Metodo para conocer todos los datos de todos los productos
	 * 
	 * @return lista con todos los productos
	 */
	public static List<Producto> lista() {
		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"SELECT idproducto,idcategoria,nombre,precio,descripcion,color,talla,stock FROM proyecto3ev.productos;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				productos.add(new Producto(rs.getInt("idproducto"),new Categoria(rs.getInt("idcategoria")), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("Descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}
	
	public static List<Producto> listaFiltro (Producto producto) {
		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"select * from productos where (color = ? or ? = '') \r\n"
					+ "and (talla = ? or ? = '') \r\n"
					+ "and (nombre like ? or ? = '');");
			pst.setString(1, producto.getColor());
			pst.setString(2, producto.getColor());
			pst.setString(3, producto.getTalla());
			pst.setString(4, producto.getTalla());
			pst.setString(5, "%" + producto.getNombre() + "%");
			pst.setString(6, producto.getNombre());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				productos.add(new Producto(new Categoria(rs.getInt("idcategoria")), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("Descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}
	
	public static List<Producto> listaFiltro2 (Producto producto) {
		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"select * from productos where color like '%?%' and talla like '%?%';");
			pst.setString(1, producto.getColor());
			pst.setString(2, producto.getTalla());
			pst.executeUpdate();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				productos.add(new Producto(new Categoria(rs.getInt("idcategoria")), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("Descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}
	
	public static List<Producto> listaFiltro3 (Producto producto) {
		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"select * from productos where color like '%?%' and talla like '%?%' and nombre like '%?%';");
			pst.setString(1, producto.getColor());
			pst.setString(2, producto.getTalla());
			pst.setString(3, producto.getNombre());
			pst.executeUpdate();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				productos.add(new Producto(new Categoria(rs.getInt("idcategoria")), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("Descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}
	
	public static List<Producto> listaStock() {
		List<Producto> productos = new ArrayList<>();
		try {
			ResultSet rs = util.Conexion.abreConexion().prepareStatement("select * from productos where stock<5")
					.executeQuery();
			while (rs.next()) {
				productos.add(new Producto(rs.getInt("idproducto"),new Categoria(rs.getInt("idcategoria")), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("Descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}

	/**
	 * Metodo para insertar nuevos productos que nos pasan ya creados
	 * 
	 * @param producto, el producto que nos pasan
	 */
	public static void inserta(Producto producto) {
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO productos (idCategoria,nombre,precio,descripcion,color,talla,stock) VALUES (?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, producto.getCategoria().getIdCategoria());
			pst.setString(2, producto.getNombre());
			pst.setDouble(3, producto.getPrecio());
			pst.setString(4, producto.getDescripcion());
			pst.setString(5, producto.getColor());
			pst.setString(6, producto.getTalla());
			pst.setInt(7, producto.getStock());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				producto.setIdProducto(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion(); 
		}
	}

	/**
	 * Metodo para eliminar stock que estan comprando
	 * 
	 * @param producto, nos pasan el producto que compran
	 * @param unidades, las unidades que compran de ese producto
	 */
	public static void eliminarStock(int producto, int unidades) {
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con
					.prepareStatement("UPDATE proyecto3ev.productos SET stock = (stock - ?) WHERE (idproducto = ?)");
			pst.setInt(1, producto);
			pst.setInt(2, unidades);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
	}

	/**
	 * Meotodo para aumentar las unidades de stock que quieres reponer cuando sea
	 * menor de 5
	 * 
	 * @param unidades, las unidades que queremos aumentar nos las pasan
	 */
	public static void aumentarStock(int unidades) {
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con
					.prepareStatement("UPDATE proyecto3ev.productos SET stock = (stock + ?) WHERE stock<5");
			pst.setInt(1, unidades);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
	}


//	public static List<Producto> listaProducto() {
//		List<Producto> productos = new ArrayList<>();
//		try {
//			ResultSet rs = Conexion.abreConexion()
//					.prepareStatement("select * from productos where nombre like '%"+a+"%'").executeQuery();
//			while (rs.next()) {
//				productos.add(new Producto(rs.getInt("idproducto"), rs.getString("nombre"), rs.getDouble("precio"),
//						rs.getString("Descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			Conexion.cierraConexion();
//		}
//		return productos;
//	}

	public static List<Producto> listaProducto(String nombre, String color, String talla) {
		List<Producto> productos = new ArrayList<>();
		try {
			ResultSet rs = util.Conexion.abreConexion()
					.prepareStatement("select * from productos where nombre like '%" + nombre + "%'").executeQuery();
			while (rs.next()) {
				productos.add(new Producto(new Categoria(rs.getInt("idcategoria")), rs.getString("nombre"),
						rs.getDouble("precio"), rs.getString("Descripcion"), rs.getString("color"),
						rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}
}
