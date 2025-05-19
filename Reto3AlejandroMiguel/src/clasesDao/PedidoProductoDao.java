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
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;
import util.Conexion;

public class PedidoProductoDao {
	/**
	 * Inserta un pedido que ya nos lo pasa creado
	 * 
	 * @param pedido nos lo pasan ya creado
	 */
	public static void inserta(PedidoProducto pedido) {
		try {
			Connection con = util.Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement(
					"INSERT INTO pedidoproducto (idpedido,idproducto,unidades,precio) VALUES (?,?,?,?);",
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

	/**
	 * Metodo para saber los pedidos que se han realizado en el mes en el que nos
	 * encontramos
	 * 
	 * @param mes Mes actual(valor numerico)
	 * @return Lista de pedidos, con los productos, realizados en el mes actual
	 */
	public static List<PedidoProducto> listaFecha(int mes) {
		List<PedidoProducto> productos = new ArrayList<>();
		try {
			PreparedStatement ps = util.Conexion.abreConexion()
					.prepareStatement("select *,d.nombre as nombrecliente,b.nombre as nombreproducto\r\n" + "from pedidoproducto a\r\n"
							+ "inner join productos b on b.idproducto=a.idproducto\r\n"
							+ "inner join pedidos c on c.idpedido=a.idpedido\r\n"
							+ "inner join clientes d on d.idcliente=c.idcliente\r\n"
							+ "where month(c.fecha)=? order by c.fecha desc");
			ps.setInt(1, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productos.add(new PedidoProducto(rs.getInt("a.idpedidoproducto"), new Pedido(new Cliente(rs.getString("nombrecliente")),rs.getInt("precioTotal"),rs.getString("direccionenvio"),rs.getDate("fecha")), new Producto(new Categoria(rs.getInt("idcategoria")),rs.getString("nombreproducto")),
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

	/**
	 * Metodo para conocer los pedidos y productos que ha realizado y comprado un
	 * cliente en especifico
	 * 
	 * @param idcliente ID unico del cliente que realiza el pedido
	 * @return Lista de pedidos con los productos que ha realizado un determinado
	 *         cliente
	 */
	public static List<PedidoProducto> listaPcliente(int idcliente) {
		List<PedidoProducto> productos = new ArrayList<>();
		try {
			PreparedStatement ps = util.Conexion.abreConexion().prepareStatement(
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

	/**
	 * Metodo para conocer los productos mas vendidos
	 * 
	 * @return Lista con los productos con mas ventas
	 */
	public static List<PedidoProducto> productoMasVendido() {
		List<PedidoProducto> productos = new ArrayList<PedidoProducto>();
		try {
			PreparedStatement ps = util.Conexion.abreConexion().prepareStatement(
					"select idproducto, SUM(unidades) as ventas from pedidoproducto GROUP BY idproducto order by ventas desc;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productos.add(new PedidoProducto(new Producto(rs.getInt("idproducto")), rs.getInt("ventas")));
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
