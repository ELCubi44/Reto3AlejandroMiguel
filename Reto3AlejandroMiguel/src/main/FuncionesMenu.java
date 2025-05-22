package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		boolean buscar = false;
		do {
			buscar = false;
			int idBuscar = funciones.dimeEntero("Selecciona una", sc);
			for (Categoria c : CategoriaDao.lista()) {
				if (c.getIdCategoria() == idBuscar) {
					buscar = true;
					pro.setCategoria(c);
					ProductoDao.inserta(pro);
				}
			}
		} while (buscar == false);
	}

	public static void altaClientes(Scanner sc) {
		Cliente cliente = new Cliente(0, funciones.dimeString("Introduce el nombre del cliente:", sc),
				funciones.dimeString("Introduce la direccion:", sc));
		int codCliente = 0;
		boolean buscar;
		do {
			buscar = true;
			codCliente = funciones.dimeEntero("Introduce el codigo:", sc);

			for (Cliente c : ClienteDao.lista()) {
				if (c.getCodigo() == codCliente) {
					buscar = false;
					System.out.println("El cliente ya existe, introduce otro:");
				}
			}
		} while (buscar == false);
		cliente.setCodigo(codCliente);
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

		for (Producto p : ProductoDao.listaFiltro(pro)) {
			System.out.println(p);
		}

	}

	public static void crearPedido(Scanner sc) {
		Cliente cliente = new Cliente();
		Pedido pedido = new Pedido();
		Producto producto = new Producto();
		List<Producto> productos = new ArrayList<>();
		boolean buscar = false;

		for (Cliente cliente2 : ClienteDao.listaPedido()) {
			System.out.println(cliente2);
		}

		do {
			int codBuscar = funciones.dimeEntero("Introduce el codigo:", sc);
			for (Cliente c : ClienteDao.lista()) {
				if (codBuscar == c.getCodigo()) {
					cliente = c;
					buscar = true;
				}
			}
		} while (buscar == false);
		System.out.println(cliente.getNombre() + " " + cliente.getCodigo());
		String pro = "";
		Boolean proBuscar = false;
		int unidades = 0;
		for (Producto producto2 : ProductoDao.listaPedido()) {
			System.out.println("Nombre: "+producto2.getNombre()+", Color: "+producto2.getColor()+", Talla: "+producto2.getTalla()+", Precio (Unidad)"+producto2.getPrecio()+", Stock: "+producto2.getStock());
		}

		do {
			proBuscar = false;
			pro = funciones.dimeString("Introduce el nombre del producto (-1 para salir):", sc);
			for (Producto p : ProductoDao.lista()) {
				if (p.getNombre().equalsIgnoreCase(pro)) {
					producto = p;
					proBuscar = true;
				}
			}
			if (proBuscar) {
				int cantidad = funciones.dimeEntero("�Cuantas unidades quieres?", sc);
				if (producto.getStock() < cantidad) {
					cantidad = producto.getStock();
					System.out.println("No hay suficiente stock, aniadiendo las unidades restantes: " + cantidad);
					ProductoDao.eliminarStock(producto.getIdProducto(), cantidad);
					unidades += cantidad;
					producto.setStock(cantidad);
					productos.add(producto);
				} else {
					ProductoDao.eliminarStock(producto.getIdProducto(), cantidad);
					unidades += cantidad;
					producto.setStock(cantidad);
					productos.add(producto);
				}
			}
			if (proBuscar = false) {
				System.out.println("El producto no existe");
			}

		} while (!pro.equalsIgnoreCase("-1"));

		if (productos.size() != 0) {
			String opcion = "";
			String dNueva = cliente.getDireccion();

			do {
				opcion = funciones
						.dimeString(cliente.getDireccion() + "\r\n�Quieres usar esta direccion de envio?(s/n)", sc);
				if (opcion.equalsIgnoreCase("n")) {
					dNueva = funciones.dimeString("Introduce la nueva direccion", sc);
				}
				if (opcion.equalsIgnoreCase("s")) {
					pedido.setDireccionEnvio(cliente.getDireccion());
				}
			} while (!opcion.equalsIgnoreCase("n") && !opcion.equalsIgnoreCase("s"));

			double precioT = unidades * producto.getPrecio();
			pedido.setPrecioTotal(precioT);
			pedido.setFecha(funciones.ldDate(LocalDate.now()));
			pedido.setCliente(cliente);
			pedido.setDireccionEnvio(dNueva);

			PedidoDao.inserta(pedido);

			for (int i = 0; i < productos.size(); i++) {
				double precioProducto = productos.get(i).getStock() * productos.get(i).getPrecio();
				PedidoProducto pedidoProducto = new PedidoProducto(pedido, productos.get(i),
						productos.get(i).getStock(), precioProducto);
				PedidoProductoDao.inserta(pedidoProducto);
			}

			System.out.println("Pedido guardado, precio total= " + pedido.getPrecioTotal());
		} else
			System.out.println("Pedido cancelado");
	}

	public static void verPedidos() {
		int mes = LocalDate.now().getMonthValue();

		for (Pedido p : PedidoDao.listaFecha(mes)) {

			System.out.println(p.getFecha() + ", cliente " + p.getCliente().getNombre() + " pedido de "
					+ p.getPrecioTotal() + " euros a " + p.getDireccionEnvio());

			for (PedidoProducto pp : PedidoProductoDao.listaPedido(p)) {
				System.out.println(pp.getProducto().getIdProducto() + pp.getProducto().getCategoria().getNombre()
						+ pp.getProducto().getNombre() + pp.getUnidades());
			}
		}

	}

	public static void bajoStock(Scanner sc) {
		for (Producto p : ProductoDao.listaStock()) {
			System.out.println(p.getNombre() + " " + p.getCategoria().getIdCategoria() + " " + p.getDescripcion() + " "
					+ p.getColor() + " " + p.getPrecio() + " " + p.getTalla() + " " + p.getStock());
		}
		for (Producto p : ProductoDao.listaStock()) {
			int reponer = funciones.dimeEntero("�Cuantas unidades quieres reponer de " + p.getNombre() + "?", sc);
			if (reponer > 0) {
				ProductoDao.aumentarStock(reponer);
				System.out.println("Producto repuesto");
			} else if (reponer <= 0)
				System.out.println("Nada que reponer");
		}
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
