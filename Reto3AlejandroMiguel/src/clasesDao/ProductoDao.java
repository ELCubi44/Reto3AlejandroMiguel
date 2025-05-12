package clasesDao;
import java.util.*;
import java.sql.*;
import clases.*;

public class ProductoDao {

	public static List<Producto> lista () {
		List <Producto> profesores = new ArrayList <Producto>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				profesores.add(new Producto );
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return profesores;
	}
	
}
