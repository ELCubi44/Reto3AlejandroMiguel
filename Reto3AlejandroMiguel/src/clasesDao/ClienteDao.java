package clasesDao;

import java.sql.*;
import java.util.*;
import clases.*;

public class ClienteDao {

	public static List<Cliente> lista () {
		List <Cliente> clientes = new ArrayList <Cliente>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idcliente,nombre,direccion,codigo FROM proyecto3ev.clientes;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				clientes.add(new Cliente (rs.getInt("idcliente"),rs.getString("nombre"),rs.getString("direccion"),rs.getInt("codigo")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	return clientes;
}
	
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
