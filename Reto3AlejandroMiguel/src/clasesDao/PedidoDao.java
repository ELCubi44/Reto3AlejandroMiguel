package clasesDao;

import java.sql.*;
import java.util.*;
import clases.*;
public class PedidoDao {
	
	public static void inserta (Pedido pedido) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO pedido ('idcliente','precioTotal','direccionEnvio','fecha') VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1,pedido.getCliente().getIdCliente());
			pst.setDouble(2, pedido.getPrecioTotal());
			pst.setString(3, pedido.getDireccionEnvio());
			pst.setDate(4, pedido.getFecha());
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
