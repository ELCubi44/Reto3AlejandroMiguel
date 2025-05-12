package clasesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import clases.Categoria;
import clases.Cliente;
import clases.Conexion;

public class ClienteDao {

	public static void inserta (Cliente cliente) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO categoria ('nombre','direccion','codigo') VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1,cliente.getNombre());
			pst.setString(2, cliente.getDireccion());
			pst.setInt(3,cliente.getCodigo());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					cliente.setIdCliente (rs.getInt(1));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	
	}
	
}
