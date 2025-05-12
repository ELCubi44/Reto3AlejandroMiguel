package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import clases.Categoria;
import clases.Cliente;
import clases.Producto;
import clasesDao.CategoriaDao;
import clasesDao.ClienteDao;
import clasesDao.ProductoDao;

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

	public static void gestionCat(Scanner sc) {
		Categoria cat = new Categoria();
		cat.setNombre(funciones.dimeString("Introduce el nombre de la categoría:", sc));
		CategoriaDao.inserta(cat);
	}

	public static void gestionPro(Scanner sc) {
		Producto pro = new Producto();
		pro.setNombre(funciones.dimeString("Introduce nombre del producto:", sc));
		pro.setColor(funciones.dimeString("Introduce color del producto:", sc));
		pro.setDescripcion(funciones.dimeString("Introduce descripción del producto", sc));
		pro.setPrecio(funciones.dimeDouble("Introduce precio del producto", sc));
		pro.setStock(funciones.dimeEntero("Introduce cantidad del producto", sc));
		pro.setTall(funciones.dimeString("Introduce talla del producto:", sc));
		System.out.println("Lista de categorías:");
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
				funciones.dimeString("Introduce la dirección:", sc), funciones.dimeEntero("Introduce el código:", sc));
		ClienteDao.inserta(cliente);
	}

	public static void busCod(Scanner sc) {
		int codBuscar = funciones.dimeEntero("Introduce el código de cliente a buscar:", sc);
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
			cliente.setDireccion(funciones.dimeString("Introduce la dirección:", sc));
			cliente.setCodigo(funciones.dimeEntero("Introduce el código:", sc));
			ClienteDao.inserta(cliente);
		}
	}

	public static void listarProductos(Scanner sc) {
		List<Categoria> categorias = CategoriaDao.lista();
		System.out.println("Lista de categorías");
		for (Categoria c : categorias) {
			System.out.println(c);
		}
		int catBuscar = funciones.dimeEntero("Elige una (Seleccione ID):", sc);
		for (Categoria c : categorias) {
			if (c.getIdCategoria() == catBuscar) {
				for (Producto p : ProductoDao.lista(catBuscar)) {
					System.out.println(p);
				}
			}
		}
	}

	public static void buscarProd(Scanner sc) {

	}
}
