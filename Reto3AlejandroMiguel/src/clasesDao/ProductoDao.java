package clasesDao;

import java.util.*;
import java.sql.*;
import clases.*;

public class ProductoDao {

	public static List<Producto> listaCategoria (int idCat) {
		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection con = Conexion.abreConexion();
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
	
	public static List<Producto> lista () {
		List <Producto> productos = new ArrayList <Producto>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idproducto,nombre,precio,descripcion,color,talla,stock FROM proyecto3ev.productos;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				productos.add(new Producto (rs.getInt("idproducto"),rs.getString("nombre"),rs.getDouble("precio"),rs.getString("Descripcion"),rs.getString("color"),rs.getString("talla"),rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return productos;
	}

	public static void inserta(Producto producto) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO producto ('idCategoria','nombre','precio','descripcion','color','talla','stock) VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, producto.getCategoria().getIdCategoria());
			pst.setString(2, producto.getNombre());
			pst.setDouble(3, producto.getIdProducto());
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
	

}
