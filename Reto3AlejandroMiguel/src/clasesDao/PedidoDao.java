package clasesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Categoria;
import clases.Conexion;

public class PedidoDao {

	public static List<Pedido> lista () {
		List <Pedido> pedidos = new ArrayList <Pedido>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pedidos.add(new Pedido ());
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
			PreparedStatement pst = con.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
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
