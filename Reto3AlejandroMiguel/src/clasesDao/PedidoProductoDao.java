 package clasesDao;

 /**
  * @author Alejandro y Miguel
  * @since 14/05/2025
  * @version 1
  */
 
import java.sql.*;
import clases.*;

public class PedidoProductoDao {
	/**
	 * Inserta un pedido que ya nos lo pasa creado
	 * @param pedido nos lo pasan ya creado
	 */
	public static void inserta (PedidoProducto pedido) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO pedido ('idpedido','idproducto','unidades','precio') VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1,pedido.getPedido().getIdPedido());
			pst.setDouble(2, pedido.getProducto().getIdProducto());
			pst.setInt(3, pedido.getUnidades());
			pst.setDouble(4, pedido.getPrecio());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					pedido.setIdPedidoProducto  (rs.getInt(1));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	
	}
	
}
