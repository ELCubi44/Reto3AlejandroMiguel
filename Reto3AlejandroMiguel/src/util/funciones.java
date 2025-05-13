package util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import clases.*;
import clasesDao.*;

public class funciones {

	public static boolean esInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean esDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int dimeEntero(String orden, Scanner sc) {
		do {
			try {
				System.out.println(orden);
				String s = sc.nextLine();
				int n = Integer.parseInt(s);
				return n;

			} catch (Exception e) {
				System.out.println("Formato incorrecto");
			}
		} while (true);
	}

	public static double dimeDouble(String orden, Scanner sc) {
		do {
			try {
				System.out.println(orden);
				String s = sc.nextLine();
				double d = Double.parseDouble(s);
				return d;

			} catch (Exception e) {
				System.out.println("Formato incorrecto");
			}
		} while (true);
	}

	public static String dimeString(String orden, Scanner sc) {
		String s = "";
		do {
			System.out.println(orden);
			s = sc.nextLine();

		} while (s.equals(""));
		return s;
	}

	public static LocalDate dimeFecha(String texto, Scanner sc) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		do {
			try {
				System.out.println(texto);
				String textoFecha = sc.nextLine();
				LocalDate fecha = LocalDate.parse(textoFecha, formato);
				return fecha;

			} catch (Exception e) {
				System.out.println("Fecha no valida");
			}

		} while (true);
	}

	public static void muestraArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(i == 0 ? array[i] : ", " + array[i]);
		}
		System.out.println();
	}

	public static void muestraArrayS(String[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(i == 0 ? array[i] : ", " + array[i]);
		}
		System.out.println();
	}

	public static void muestraArrayListInt(ArrayList<Integer> lista) {
		for (int i = 0; i < lista.size(); i++) {
			System.out.print(i == 0 ? lista.get(i) : ", " + lista.get(i));
		}
		System.out.println();
	}

	public static void muestraListaInt(List<Integer> lista) {
		for (int i = 0; i < lista.size(); i++) {
			System.out.print(i == 0 ? lista.get(i) : ", " + lista.get(i));
		}
		System.out.println();
	}

	public static boolean esPrimo(int n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static double redondea(double d) {
		return Math.round(d * 100) / (double) 100;
	}

	public static String DateAString(Date fechaDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaString = sdf.format(fechaDate);
		return fechaString;
	}

	public static String DateAStringAnio(Date fechaDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String fechaString = sdf.format(fechaDate);
		return fechaString;
	}

	public static Date StringADate(String fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			return sdf.parse(fecha);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date StringADateAnio(String fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			sdf.setLenient(false);
			return sdf.parse(fecha);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDate DateLd(Date fDate) {
		return fDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date ldDate(LocalDate fLocalDate) {
		Date d = Date.from(fLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return new Date(d.getTime());
	}

	public static java.sql.Date convierteFecha(Date fecha)
	{
		return new java.sql.Date(fecha.getTime());
	}
	
	public static void gestionCat(Scanner sc) {
		Categoria cat = new Categoria();
		cat.setNombre(funciones.dimeString("Introduce el nombre de la categor�a:", sc));
		CategoriaDao.inserta(cat);
	}

	public static void gestionPro(Scanner sc) {
		Producto pro = new Producto();
		pro.setNombre(funciones.dimeString("Introduce nombre del producto:", sc));
		pro.setColor(funciones.dimeString("Introduce color del producto:", sc));
		pro.setDescripcion(funciones.dimeString("Introduce descripci�n del producto", sc));
		pro.setPrecio(funciones.dimeDouble("Introduce precio del producto", sc));
		pro.setStock(funciones.dimeEntero("Introduce cantidad del producto", sc));
		pro.setTall(funciones.dimeString("Introduce talla del producto:", sc));
		System.out.println("Lista de categor�as:");
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
				funciones.dimeString("Introduce la direcci�n:", sc), funciones.dimeEntero("Introduce el c�digo:", sc));
		ClienteDao.inserta(cliente);
	}

	public static void busCod(Scanner sc) {
		int codBuscar = funciones.dimeEntero("Introduce el c�digo de cliente a buscar:", sc);
		boolean buscar = false;
		for (Cliente c : ClienteDao.lista()) {
			if (c.getCodigo() == codBuscar) {
				System.out.println(c);
				buscar = true;
			}
		}
		if (buscar == false) {
			System.out.println("No existe, creando uno nuevo...");
			Cliente cliente = new Cliente();
			cliente.setNombre(funciones.dimeString("Introduce el nombre del cliente:", sc));
			cliente.setDireccion(funciones.dimeString("Introduce la direcci�n:", sc));
			cliente.setCodigo(funciones.dimeEntero("Introduce el c�digo:", sc));
			ClienteDao.inserta(cliente);
		}
	}

	public static void listarProductos(Scanner sc) {
		List<Categoria> categorias = CategoriaDao.lista();
		System.out.println("Lista de categor�as");
		for (Categoria c : categorias) {
			System.out.println(c);
		}
		int catBuscar = funciones.dimeEntero("Elige una (Seleccione ID):", sc);
		for (Categoria c : categorias) {
			if (c.getIdCategoria() == catBuscar) {
				for (Producto p : ProductoDao.listaCategoria(catBuscar)) {
					System.out.println(p);
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
		// terminar
	}

	public static void crearPedido(Scanner sc) {
		List <Cliente> clientes = ClienteDao.lista();
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
		
		int clienteBuscar;
		boolean aux = false;
		do {
		clienteBuscar = funciones.dimeEntero("Elige una (Seleccione ID):", sc);
			for (Cliente cliente : clientes) {
				if (clienteBuscar == cliente.getIdCliente());
				aux = true;
			}
		} while (aux == false);
		
		for (Cliente cliente : clientes) {
			if (clienteBuscar == cliente.getIdCliente()); {
				System.out.println ("Nombre: "+cliente.getIdCliente()+", ID: "+clienteBuscar);
			}
		}
		
			
		List<Producto> pedido = new ArrayList <Producto>();
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
							Producto productoPedido = new Producto (producto,unidades);
							pedido.add(productoPedido);
							ProductoDao.eliminarStock(producto, unidades);
						}
						else if (unidades <= 0){
							System.out.println("Las unidades deben ser mas de 0");
						}
						else {
							Producto productoPedido = new Producto (producto,unidades);
							pedido.add(productoPedido);
							ProductoDao.eliminarStock(producto, unidades);
						}
				}
				else 
					System.out.println("Este Id no existe");
			}
		} while (true);
		
		String direccion = "";
		
		Cliente cliente = null;
		for (Cliente cliente2 : clientes) {
			if (cliente2.getIdCliente()==clienteBuscar) {
				cliente = cliente2;
				direccion = cliente2.getDireccion();
				System.out.println(cliente2.getDireccion());
				System.out.println("Esta es tu direccion de envio, ¿deseas cambiarla? (Si / No):");
			}
		}
		
		do {
			String opcion = sc.nextLine().toLowerCase();
			if (opcion.equals("si")) {
				System.out.println("Escribe la nueva direccion de envio:");
				direccion = sc.nextLine();
				System.out.println("Direccion de envio actualizada");
				break;
			}
			else 
				System.out.println("Seleccionada direccion de envio predeterminada");
				break;
		} while (true);
		
		double precio = 0;
		for (Producto producto : pedido) {
			precio += (producto.getPrecio()*producto.getStock());
		}
		LocalDate now = LocalDate.now();
		Date hoy = ldDate (now);
		
		Pedido pedido1 = new Pedido (cliente,precio,direccion,(java.sql.Date) hoy);
		
	} 
		

	public static void verPedidos(Scanner sc) {

	}
}
