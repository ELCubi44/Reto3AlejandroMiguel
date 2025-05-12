package clasesDao;
import java.sql.*;
import java.util.*;
import clases.*;

public class CategoriaDao {
	
	public static List<Categoria> lista () {
		List <Categoria> categorias = new ArrayList <Categoria>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idcategoria,nombre FROM proyecto3ev.categorias;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				categorias.add(new Categoria (rs.getInt("idcategoria"),rs.getString("nombre")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return categorias;
	}
	
	public static void inserta (String categoria) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO categoria ('nombre') VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1,categoria);
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					Categoria categoria1 = new Categoria (rs.getInt(1),categoria);
					categorias.add(categoria1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	
	}
}
