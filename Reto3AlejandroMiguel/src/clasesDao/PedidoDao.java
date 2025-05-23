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
import clases.Pedido;
import util.Conexion;
import util.funciones;

public class PedidoDao {
	/**
	 * Metodo que lista todos los pedidos que de la base de datos
	 * 
	 * @return una lista con todos los pedidos almacenados
	 */
	public static List<Pedido> lista() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"SELECT idpedido,p.idcliente,codigo,precioTotal,direccionEnvio,fecha FROM pedidos p "
					+ "inner join clientes c on p.idcliente = c.idcliente;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pedidos.add(new Pedido(rs.getInt("idpedido"), new Cliente(rs.getInt("p.idcliente"),rs.getInt("codigo")),
						rs.getDouble("precioTotal"), rs.getString("direccionEnvio"), rs.getDate("fecha")));
			}
			rs.close();
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return pedidos;
	}

	public static List<Pedido> listaFecha(int fecha) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"select p.idpedido, p.fecha, c.nombre, p.precioTotal, p.direccionEnvio from pedidos "
					+ "p inner join clientes c on c.idcliente = p.idcliente where month (p.fecha) = ? order by p.fecha desc;");
			pst.setInt(1, fecha);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pedidos.add(new Pedido(rs.getInt("idpedido"),new Cliente(rs.getString("nombre")),rs.getDouble("precioTotal"), rs.getString("direccionEnvio"), rs.getDate("fecha")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return pedidos;
	}

	/**
	 * Metodo que inserta un pedido que nos pasan a la base de datos
	 * 
	 * @param pedido un pedido ya creado
	 */
	public static void inserta(Pedido pedido) {
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO pedidos (idcliente,precioTotal,direccionEnvio,fecha) VALUES (?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, pedido.getCliente().getIdCliente());
			pst.setDouble(2, pedido.getPrecioTotal());
			pst.setString(3, pedido.getDireccionEnvio());
			pst.setDate(4, funciones.convierteFecha(pedido.getFecha()));
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				pedido.setIdPedido(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}

	}

}
