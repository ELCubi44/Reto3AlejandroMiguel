package main;

import java.time.LocalDate;
import java.util.*;

import clases.Categoria;
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;
import clasesDao.CategoriaDao;
import clasesDao.ClienteDao;
import clasesDao.PedidoDao;
import clasesDao.PedidoProductoDao;
import clasesDao.ProductoDao;
import util.funciones;

public class FuncionesMenu {

	public static void gestionCat(Scanner sc) {
		Categoria cat = new Categoria();
		cat.setNombre(funciones.dimeString("Introduce el nombre de la categoria:", sc));
		CategoriaDao.inserta(cat);
	}

	public static void gestionPro(Scanner sc) {
		Producto pro = new Producto();
		pro.setNombre(funciones.dimeString("Introduce nombre del producto:", sc));
		pro.setColor(funciones.dimeString("Introduce color del producto:", sc));
		pro.setDescripcion(funciones.dimeString("Introduce descripcion del producto", sc));
		pro.setPrecio(funciones.dimeDouble("Introduce precio del producto", sc));
		pro.setStock(funciones.dimeEntero("Introduce cantidad del producto", sc));
		pro.setTall(funciones.dimeString("Introduce talla del producto:", sc));
		System.out.println("Lista de categorias:");
		for (Categoria c : CategoriaDao.lista()) {
			System.out.println(c.getIdCategoria() + c.getNombre());
		}
		int idBuscar = funciones.dimeEntero("Selecciona una", sc);
		for (Categoria c : CategoriaDao.lista()) {
			if (c.getIdCategoria() == idBuscar) {
				pro.setCategoria(c);
				ProductoDao.inserta(pro);
			}
		}
	}

	public static void altaClientes(Scanner sc) {
		Cliente cliente = new Cliente(0, funciones.dimeString("Introduce el nombre del cliente:", sc),
				funciones.dimeString("Introduce la direccion:", sc), funciones.dimeEntero("Introduce el codigo:", sc));
		ClienteDao.inserta(cliente);
	}

	public static void busCod(Scanner sc) {
		int codBuscar = funciones.dimeEntero("Introduce el codigo de cliente a buscar:", sc);
		boolean buscar = false;
		for (Cliente c : ClienteDao.lista()) {
			if (c.getCodigo() == codBuscar) {
				System.out
						.println(c.getIdCliente() + " " + c.getNombre() + " " + c.getDireccion() + " " + c.getCodigo());
				buscar = true;
			}
		}
		if (buscar == false) {
			System.out.println("No existe, creando uno nuevo...");
			Cliente cliente = new Cliente();
			cliente.setNombre(funciones.dimeString("Introduce el nombre del cliente:", sc));
			cliente.setDireccion(funciones.dimeString("Introduce la direccion:", sc));
			cliente.setCodigo(funciones.dimeEntero("Introduce el codigo:", sc));
			ClienteDao.inserta(cliente);
		}
	}

	public static void listarProductos(Scanner sc) {
		List<Categoria> categorias = CategoriaDao.lista();
		System.out.println("Lista de categorias");
		for (Categoria c : categorias) {
			System.out.println(c.getIdCategoria() + " " + c.getNombre());
		}
		int catBuscar = funciones.dimeEntero("Elige una (Seleccione ID):", sc);
		for (Categoria c : categorias) {
			if (c.getIdCategoria() == catBuscar) {
				for (Producto p : ProductoDao.listaCategoria(catBuscar)) {
					System.out.println(p.getNombre() + " " + p.getDescripcion() + " " + p.getPrecio() + " "
							+ p.getTalla() + " " + p.getColor() + " " + p.getStock());
				}
			}
		}
	}


//	public static void buscarProd(Scanner sc) {
//		Producto pro = new Producto();
//		System.out.println("Introduce el nombre del producto:");
//		String nombre = sc.nextLine();
//		pro.setNombre(nombre);
//		System.out.println("Introduce el color del producto:");
//		String color = sc.nextLine();
//		pro.setColor(color);
//		System.out.println("Introduce la talla del producto:");
//		String talla = sc.nextLine();
//		pro.setTall(talla);
//		for (Producto p : lista) {
//			System.out.println(p);
//		}
//	}

	public static void buscarProd(Scanner sc) {
		Producto pro = new Producto();
		System.out.println("Introduce el nombre del producto:");
		String nombre = sc.nextLine();
		pro.setNombre(nombre);
		System.out.println("Introduce el color del producto:");
		String color = sc.nextLine();
		pro.setColor(color);
		System.out.println("Introduce la talla del producto:");
		String talla = sc.nextLine();
		pro.setTall(talla);
		for (Producto p : ProductoDao.listaProducto(nombre, color, talla)) {
			System.out.println(p);
		}
	}

	public static void crearPedido(Scanner sc) {
		List<Cliente> clientes = ClienteDao.lista();
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

		int clienteBuscar;
		boolean aux = false;
		do {
			clienteBuscar = funciones.dimeEntero("Elige una (Seleccione ID):", sc);
			for (Cliente cliente : clientes) {
				if (clienteBuscar == cliente.getIdCliente())
					;
				aux = true;
			}
		} while (aux == false);

		for (Cliente cliente : clientes) {
			if (clienteBuscar == cliente.getIdCliente())
				;
			{
				System.out.println("Nombre: " + cliente.getIdCliente() + ", ID: " + clienteBuscar);
			}
		}

		List<Producto> pedido = new ArrayList<Producto>();
		do {
			List<Producto> productos = ProductoDao.lista();
			for (Producto producto : productos) {
				System.out.println(producto);
			}
			int producto = funciones.dimeEntero("Elige un producto (Seleccione ID) o 0 para acabar", sc);
			if (producto == 0)
				break;
			for (Producto producto2 : productos) {
				if (producto2.getIdProducto() == producto) {
					int unidades = funciones.dimeEntero("Cuantas unidades quieres", sc);
					if (unidades > producto2.getStock()) {
						unidades = producto2.getStock();
						Producto productoPedido = new Producto(producto, unidades);
						pedido.add(productoPedido);
						ProductoDao.eliminarStock(producto, unidades);
					} else if (unidades <= 0) {
						System.out.println("Las unidades deben ser mas de 0");
					} else {
						Producto productoPedido = new Producto(producto, unidades);
						pedido.add(productoPedido);
						ProductoDao.eliminarStock(producto, unidades);
					}
				} else
					System.out.println("Este Id no existe");
			}
		} while (true);

		String direccion = "";

		Cliente cliente = null;
		for (Cliente cliente2 : clientes) {
			if (cliente2.getIdCliente() == clienteBuscar) {
				cliente = cliente2;
				direccion = cliente2.getDireccion();
				System.out.println(cliente2.getDireccion());
				System.out.println("Esta es tu direccion de envio, �deseas cambiarla? (Si / No):");
			}
		}

		do {
			String opcion = sc.nextLine().toLowerCase();
			if (opcion.equals("si")) {
				System.out.println("Escribe la nueva direccion de envio:");
				direccion = sc.nextLine();
				System.out.println("Direccion de envio actualizada");
				break;
			} else
				System.out.println("Seleccionada direccion de envio predeterminada");
			break;
		} while (true);

		double precio = 0;
		for (Producto producto : pedido) {
			precio += (producto.getPrecio() * producto.getStock());
		}
		LocalDate now = LocalDate.now();
		Date hoy = funciones.ldDate(now);

		Pedido pedido1 = new Pedido(cliente, precio, direccion, funciones.convierteFecha(hoy));
		PedidoDao.inserta(pedido1);
		for (int i = 0; i < pedido.size(); i++) {
			PedidoProducto pedidoProducto = new PedidoProducto(pedido1, pedido.get(i), pedido.get(i).getStock(),
					pedido.get(i).getPrecio() * pedido.get(i).getStock());
			PedidoProductoDao.inserta(pedidoProducto);
		}
		System.out.println("Pedido guardado, el precio total es: " + precio);
	}

	public static void verPedidos() {
		int mes = LocalDate.now().getMonthValue();
		for (PedidoProducto p : PedidoProductoDao.listaFecha(mes)) {
			System.out.println(p.getPedido().getFecha() + " " + p.getPedido().getCliente().getNombre() + " "
					+ p.getPedido().getPrecioTotal() + " " + p.getPedido().getDireccionEnvio() + " "
					+ p.getProducto().getCategoria().getIdCategoria() + " " + p.getProducto().getNombre() + " "
					+ p.getUnidades());
		}
	}

	public static void bajoStock(Scanner sc) {
		for (Producto p : ProductoDao.listaStock()) {
			System.out.println(p.getNombre() + " " + p.getCategoria().getIdCategoria() + " " + p.getDescripcion() + " "
					+ p.getColor() + " " + p.getPrecio() + " " + p.getTalla() + " " + p.getStock());
		}
		int reponer = funciones.dimeEntero("�Cuantas unidades quieres reponer?", sc);
		if (reponer <= 0) {
			System.out.println("Nada que reponer");
		} else
			ProductoDao.aumentarStock(reponer);
		System.out.println("Producto repuesto");
	}

	public static void pedidosCliente(Scanner sc) {
		int id = funciones.dimeEntero("Introduce tu ID de cliente:", sc);
		List<PedidoProducto> lista = PedidoProductoDao.listaPcliente(id);
		boolean buscar = false;
		for (Cliente c : ClienteDao.lista()) {
			if (c.getIdCliente() == id) {
				buscar = true;
			}
		}
		if (buscar) {
			if (lista != null) {
				for (PedidoProducto p : lista) {
					System.out.println(p.getPedido().getDireccionEnvio() + " " + p.getPedido().getFecha() + " "
							+ p.getPedido().getPrecioTotal() + " " + p.getProducto().getCategoria().getIdCategoria()
							+ " " + p.getProducto().getNombre() + " " + p.getProducto().getStock() + " "
							+ p.getUnidades());
				}
			} else
				System.out.println("Lista vacia");
		} else
			System.out.println("No existe cliente");
	}

	public static void productoMvendido() {
		List<Producto> productos = ProductoDao.lista();
		List<PedidoProducto> productosVendidosOrdenados = PedidoProductoDao.productoMasVendido();
		List<Producto> masVendidos = new ArrayList<Producto>();
		masVendidos.add(productosVendidosOrdenados.get(0).getProducto());
		int unidadesMasVendido = productosVendidosOrdenados.get(0).getUnidades();
		masVendidos.remove(0);
		for (PedidoProducto pedidoProducto : productosVendidosOrdenados) {
			if (pedidoProducto.getUnidades() == unidadesMasVendido)
				masVendidos.add(pedidoProducto.getProducto());
		}
		for (Producto producto1 : productos) {
			for (Producto producto2 : masVendidos) {
				if (producto1.getIdProducto() == producto2.getIdProducto())
					System.out.println("Estas son las caracteristicas del producto mas vendido: \r\n" + "Categoria:"
							+ producto1.getCategoria().getNombre() + ", Nombre:" + producto1.getNombre() + ", Stock:"
							+ producto1.getStock());
			}
		}

	}
}
