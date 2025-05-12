package clasesDao;
import java.sql.*;
import clases.*;

public class CategoriaDao {

	public static void inserta (Categoria categoria) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO categoria ('nombre') VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, categoria.getNombre());
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
