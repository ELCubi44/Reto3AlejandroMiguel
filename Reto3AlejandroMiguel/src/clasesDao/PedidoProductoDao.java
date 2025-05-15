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

import clases.Categoria;
import clases.Conexion;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;

public class PedidoProductoDao {
	/**
	 * Inserta un pedido que ya nos lo pasa creado
	 * 
	 * @param pedido nos lo pasan ya creado
	 */
	public static void inserta(PedidoProducto pedido) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO pedido ('idpedido','idproducto','unidades','precio') VALUES (?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, pedido.getPedido().getIdPedido());
			pst.setDouble(2, pedido.getProducto().getIdProducto());
			pst.setInt(3, pedido.getUnidades());
			pst.setDouble(4, pedido.getPrecio());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				pedido.setIdPedidoProducto(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}

	}

	public static List<PedidoProducto> listaFecha(int mes) {
		List<PedidoProducto> productos = new ArrayList<>();
		try {
			PreparedStatement ps = Conexion.abreConexion()
					.prepareStatement("select *\r\n" + "from pedidoproducto a\r\n"
							+ "inner join productos b on b.idproducto=a.idproducto\r\n"
							+ "inner join pedidos c on c.idpedido=a.idpedido\r\n"
							+ "where month(c.fecha)=? order by c.fecha desc");
			ps.setInt(1, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productos.add(new PedidoProducto(rs.getInt("a.idpedidoproducto"), new Pedido(), new Producto(),
						rs.getInt("a.unidades"), rs.getDouble("a.precio")));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}

	public static List<PedidoProducto> listaPcliente(int idcliente) {
		List<PedidoProducto> productos = new ArrayList<>();
		try {
			PreparedStatement ps = Conexion.abreConexion().prepareStatement(
					"select b.fecha,b.precioTotal,b.direccionEnvio,a.unidades,d.nombre,d.idcategoria from pedidoproducto a \r\n"
							+ "inner join pedidos b on b.idpedido=a.idpedido\r\n"
							+ "inner join clientes c on c.idcliente=b.idcliente\r\n"
							+ "inner join productos d on d.idproducto=a.idproducto\r\n" + "where c.idcliente=?");
			ps.setInt(1, idcliente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productos.add(new PedidoProducto(
						new Pedido(rs.getDouble("b.precioTotal"), rs.getString("b.direccionEnvio"),
								rs.getDate("b.fecha")),
						new Producto(new Categoria(rs.getInt("d.idcategoria")), rs.getString("d.nombre"),
								rs.getInt("d.stock")),
						rs.getInt("a.unidades")));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return productos;
	}
}
