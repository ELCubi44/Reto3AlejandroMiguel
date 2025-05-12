package main;

import java.util.Scanner;

import util.funciones;

public class inicio {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero(
					"Menú:\r\n1-Mantenimientos\r\n2-Catálogo de productos\r\n3-Pedidos\r\n4-Informes\r\n5-Salir", sc);
			switch (opcion) {
			case 1:
				submenu1(sc);
			case 2:
				submenu2(sc);
				break;
			case 3:
				submenu3(sc);
				break;
			case 4:
				submenu4(sc);
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 5);

	}

	public static void submenu1(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero(
					"1-Gestión de categorías\r\n2-Gestión de productos\r\n3-Gestión de clientes\r\n4-Volver", sc);
			switch (opcion) {
			case 1:
				funciones.gestionCat(sc);
				break;
			case 2:
				funciones.gestionPro(sc);
				break;
			case 3:
				submenu11(sc);
				break;
			case 4:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 4);
	}

	public static void submenu11(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Alta de nuevos clientes\r\n2-Búsqueda por código\r\n3-Volver", sc);
			switch (opcion) {
			case 1:
				funciones.altaClientes(sc);
				break;
			case 2:
				funciones.busCod(sc);
				break;
			case 3:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 3);
	}

	public static void submenu2(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Listar productos por categoría\r\n2-Buscar productos\r\n3-Volver", sc);
			switch (opcion) {
			case 1:
				funciones.listarProductos(sc);
				break;
			case 2:
				funciones.buscarProd(sc);
				break;
			case 3:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 3);
	}

	public static void submenu3(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Crear pedido\r\n2-Ver pedidos\r\n3-Volver", sc);
			switch (opcion) {
			case 1:
				funciones.crearPedido(sc);
				break;
			case 2:
				funciones.verPedidos(sc);
				break;
			case 3:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 3);
	}

	public static void submenu4(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Bajo stock\r\n2-Pedidos por cliente\r\n3-Producto más vendido\r\n4-Volver",
					sc);
			switch (opcion) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (opcion != 4);
	}

}
