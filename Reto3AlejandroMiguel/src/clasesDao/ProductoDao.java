package clasesDao;
import java.util.*;
import java.sql.*;
import clases.*;

public class ProductoDao {

//	public static List<Producto> lista () {
//		List <Producto> productos = new ArrayList <Producto>();
//		try {
//			Connection con = Conexion.abreConexion();
//			PreparedStatement pst = con.prepareStatement("SELECT idproducto,idcategoria,nombre,precio,descripcion,color,talla,stock FROM proyecto3ev.productos;");
//			ResultSet rs = pst.executeQuery();
//			while (rs.next()) {
//				productos.add(new Producto (rs.getInt("idproducto"),rs.get));
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			Conexion.cierraConexion();
//		}
//		return profesores;
//	}
	
	public static void inserta (Producto producto) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO producto ('idCategoria','nombre','precio','descripcion','color','talla','stock) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1,producto.getCategoria().getIdCategoria());
			pst.setString(2, producto.getNombre());
			pst.setDouble(3, producto.getIdProducto());
			pst.setString(4, producto.getDescripcion());
			pst.setString(5, producto.getColor());
			pst.setString(6, producto.getTalla());
			pst.setInt(7, producto.getStock());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					producto.setIdProducto  (rs.getInt(1));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	}
	
}
