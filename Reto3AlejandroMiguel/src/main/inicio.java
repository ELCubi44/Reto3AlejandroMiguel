package main;

import java.util.Scanner;

import util.funciones;

public class inicio {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero(
					"Men�:\r\n1-Mantenimientos\r\n2-Cat�logo de productos\r\n3-Pedidos\r\n4-Informes\r\n5-Salir", sc);
			switch (opcion) {
			case 1:
				submenu1(sc);
				break;
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
				System.out.println("Opci�n no v�lida");
				break;
			}
		} while (opcion != 5);

	}

	public static void submenu1(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero(
					"1-Gesti�n de categor�as\r\n2-Gesti�n de productos\r\n3-Gesti�n de clientes\r\n4-Volver", sc);
			switch (opcion) {
			case 1:
				FuncionesMenu.gestionCat(sc);
				break;
			case 2:
				FuncionesMenu.gestionPro(sc);
				break;
			case 3:
				submenu11(sc);
				break;
			case 4:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opci�n no v�lida");
				break;
			}
		} while (opcion != 4);
	}

	public static void submenu11(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Alta de nuevos clientes\r\n2-B�squeda por c�digo\r\n3-Volver", sc);
			switch (opcion) {
			case 1:
				FuncionesMenu.altaClientes(sc);
				break;
			case 2:
				FuncionesMenu.busCod(sc);
				break;
			case 3:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opci�n no v�lida");
				break;
			}
		} while (opcion != 3);
	}

	public static void submenu2(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Listar productos por categor�a\r\n2-Buscar productos\r\n3-Volver", sc);
			switch (opcion) {
			case 1:
				FuncionesMenu.listarProductos(sc);
				break;
			case 2:
//				FuncionesMenu.buscarProd(sc);
				break;
			case 3:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opci�n no v�lida");
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
				FuncionesMenu.crearPedido(sc);
				break;
			case 2:
				FuncionesMenu.verPedidos();
				break;
			case 3:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opci�n no v�lida");
				break;
			}
		} while (opcion != 3);
	}

	public static void submenu4(Scanner sc) {
		int opcion = 0;
		do {
			opcion = funciones.dimeEntero("1-Bajo stock\r\n2-Pedidos por cliente\r\n3-Producto m�s vendido\r\n4-Volver",
					sc);
			switch (opcion) {
			case 1:
				FuncionesMenu.bajoStock(sc);
				break;
			case 2:
				FuncionesMenu.pedidosCliente(sc);
				break;
			case 3:
				FuncionesMenu.productoMvendido();
				break;
			case 4:
				System.out.println("Volviendo...");
				break;
			default:
				System.out.println("Opci�n no v�lida");
				break;
			}
		} while (opcion != 4);
	}

}
