package main;

import java.util.Scanner;

import util.funciones;

public class inicio {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		int opcion2 = 0;
		int opcion3 = 0;
		do {
			opcion = funciones.dimeEntero(
					"1-Mantenimientos\r\n2-Cat�logo de productos\r\n3-Pedidos\r\n4-Informes\r\n5-Salir", sc);
			switch (opcion) {
			case 1:
				opcion2 = funciones
						.dimeEntero("1-Gesti�n de categor�as\r\n2-Gesti�n de productos\r\n3-Gesti�n de clientes", sc);
				switch (opcion2) {
				case 1:

					break;
				case 2:

					break;
				case 3:
					opcion3 = funciones.dimeEntero("1-Alta de nuevos clientes\r\n2-B�squeda por c�digo", sc);
					switch (opcion3) {
					case 1:

						break;
					case 2:

						break;
					}
					break;
				}
				break;
			case 2:
				opcion2 = funciones.dimeEntero("1-Listar productos por categor�a\r\n2-Buscar productos", sc);
				switch (opcion2) {
				case 1:

					break;
				case 2:

					break;
				}
				break;
			case 3:
				opcion2 = funciones.dimeEntero("1-Crear pedido\r\n2-Ver pedidos", sc);
				switch (opcion2) {
				case 1:

					break;
				case 2:

					break;
				}
				break;

			case 4:
				opcion2 = funciones.dimeEntero("1-Bajo stock\r\n2-Pedidos por cliente\r\n3-Producto m�s vendido", sc);
				switch (opcion2) {
				case 1:

					break;
				case 2:

					break;
				}
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			}
		} while (opcion != 5);

	}
}
