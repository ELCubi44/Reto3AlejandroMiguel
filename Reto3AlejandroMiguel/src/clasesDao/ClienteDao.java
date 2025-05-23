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

import clases.Cliente;
import util.Conexion;

public class ClienteDao {
	/**
	 * Metodo que lista todos los clientes de la base de datos
	 * 
	 * @return una lista con los clientes de la base de datos
	 */
	public static List<Cliente> lista() { 
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con
					.prepareStatement("SELECT idcliente,nombre,direccion,codigo FROM proyecto3ev.clientes;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				clientes.add(new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("direccion"),
						rs.getInt("codigo")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return clientes;
	}
	
	public static List<Cliente> listaPedido() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con
					.prepareStatement("SELECT nombre,codigo FROM proyecto3ev.clientes;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				clientes.add(new Cliente(rs.getString("nombre"), rs.getInt("codigo")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return clientes;
	}

	/**
	 * Metodo que sirve oara insertar un cliente nuevo en la base de datos
	 * 
	 * @param cliente nos pasan el cliente que queremos añadir ya creado
	 */
	public static void inserta(Cliente cliente) {
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO clientes (nombre,direccion,codigo) VALUES (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cliente.getNombre());
			pst.setString(2, cliente.getDireccion());
			pst.setInt(3, cliente.getCodigo());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cliente.setIdCliente(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}

	}

}
