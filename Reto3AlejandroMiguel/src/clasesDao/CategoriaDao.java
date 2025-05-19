package clasesDao;

/**
 * @author Alejandro y Miguel
 * @since 14/05/2025
 * @version 1
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Categoria;
import util.Conexion;

public class CategoriaDao {
	/**
	 * Metodo para listar las categorias
	 * @return una lista con las categorias de la base de datos insertada
	 */
	public static List<Categoria> lista () {
		List <Categoria> categorias = new ArrayList <Categoria>();
		try {
			Connection con = util.Conexion.abreConexion();
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
	/**
	 * Sirve para insertar una categoria nueva en la base de datos
	 * @param categoria nos pasan la categoria creada que queremos añadir
	 */
	public static void inserta (Categoria categoria) {
		try {
			Connection con = util.Conexion.abreConexion();
<<<<<<< HEAD
			PreparedStatement pst = con.prepareStatement("INSERT INTO categorias (nombre) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
=======
			PreparedStatement pst = con.prepareStatement("INSERT INTO categoria ('nombre') VALUES ?;", Statement.RETURN_GENERATED_KEYS);
>>>>>>> branch 'main' of https://github.com/ELCubi44/Reto3AlejandroMiguel.git
			pst.setString(1,categoria.getNombre());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					categoria.setIdCategoria  (rs.getInt(1));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	
	}
}
