package clasesDao;

import java.sql.*;
import java.util.*;
import clases.*;
public class PedidoDao {

	public static List<Pedido> lista () {
		List <Pedido> pedidos = new ArrayList <Pedido>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idpedido,idcliente,precioTotal,direccionEnvio,fecha FROM proyecto3ev.pedidos;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pedidos.add(new Pedido (rs.getInt("idpedido"),rs.getInt("idcliente"),rs.getDouble("precioTotal"),rs.getString("direccionEnvio"),rs.getDate("fecha")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return pedidos;
	}
	
	public static void inserta (Pedido pedido) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO pedido ('idcliente','precioTotal','direccionEnvio','fecha') VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			pst.setString();
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					pedido.setIdPedido  (rs.getInt(1));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	
	}
	
}
