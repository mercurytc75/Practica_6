package PRACTICA6;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AppPractica {
	private static final Scanner sc = new Scanner(System.in);
	private static final ArbolProductos arbol = new ArbolProductos();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String rutaArchivo = "productos.csv";
		
		lectorproductosCSV.cargarDesdeArchivo(rutaArchivo, arbol);
		
		int opcion;
		do {
			mostrarMenu();
			//
			opcion = leerEntero("elige una opcion: ");
			
			switch (opcion) {
			case 1 -> agregarNuevoProducto();
			case 2 -> buscarProducto();
            case 3 -> realizarVenta();
            case 4 -> realizarCompra();
            case 5 -> imprimirInversion();
            case 6 ->   {
                System.out.println("Mostrando productos (in-order):");
                arbol.imprimirInOrden();
                        }
            case 0 -> System.out.println("Saliendo de la aplicación...");
			default -> System.out.println("opcion invalida.");
			}
			
			System.out.println();
			
		}while(opcion != 0);
	}

	private static void mostrarMenu() {
        System.out.println("========== CATÁLOGO DE PRODUCTOS ==========");
		System.out.println("1. Agregar nuevo producto");
        System.out.println("2. Buscar producto por clave");
		System.out.println("3. Realizar venta de producto");
		System.out.println("4. Realizar compra (entrada de inventario)");
		System.out.println("5. Imprimir inversión total en la empresa");
		System.out.println("6. Imprimir todos los productos (in-order)");
		System.out.println("0. Salir");
        System.out.println("===========================================");
	}
	
	
	
	
	private static int leerEntero(String mensaje) {
        int x = 0;
		boolean valido = false;
		do {
			try {
				System.out.print(mensaje);
				x = sc.nextInt();
	            sc.nextLine(); // limpiar buffer
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println(">> Debes capturar un número entero.");
	            sc.nextLine(); // limpiar lo que metió mal
			}
		} while (!valido);
	return x;
	}

	private static double leerDouble(String mensaje) {
        double x = 0;
		boolean valido = false;
		do {
			try {
				System.out.print(mensaje);
				x = sc.nextDouble();
	            sc.nextLine(); // limpiar buffer
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println(">> Debes capturar un número (puede ser decimal).");
				sc.nextLine();
			}
		} while (!valido);
		return x;
	}

	private static void agregarNuevoProducto() {
		System.out.println("---- Agregar nuevo producto ----");
		int clave = leerEntero("Clave (entero, única): ");
		System.out.print("Descripción: ");
		String descripcion = sc.nextLine();
		int inventario = leerEntero("Inventario inicial: ");
		double costo = leerDouble("Costo unitario: ");
		double precio = leerDouble("Precio de venta: ");

		if (precio <= costo) {
			System.out.println(">> El precio de venta debe ser mayor al costo unitario.");
			return;
		}

		Producto p = new Producto(clave, descripcion, inventario, costo, precio);
		arbol.insertar(p);
	}

	private static void buscarProducto() {
		System.out.println("---- Buscar producto ----");
		int clave = leerEntero("Clave del producto: ");
		Producto p = arbol.buscar(clave);
		if (p == null) {
			System.out.println(">> No se encontró producto con la clave " + clave);
		} else {
			System.out.println("Producto encontrado:");
			System.out.println(p);
		}
	}

	private static void realizarVenta() {
		System.out.println("---- Venta de producto ----");
		int clave = leerEntero("Clave del producto: ");
		int cantidad = leerEntero("Cantidad a vender: ");

		double total = arbol.vender(clave, cantidad);
		if (total >= 0) {
		System.out.println("Venta realizada correctamente.");
		System.out.println("Total a pagar: $" + total);
		} else {
		System.out.println("No se pudo realizar la venta.");
		}
	}

	private static void realizarCompra() {
		System.out.println("---- Compra de producto ----");
		int clave = leerEntero("Clave del producto: ");
		int cantidad = leerEntero("Cantidad a comprar: ");

		boolean ok = arbol.comprar(clave, cantidad);
		if (ok) {
			System.out.println("Compra registrada. Inventario actualizado.");
		} else {
		System.out.println("No se pudo registrar la compra.");
		}
	}

	private static void imprimirInversion() {
		System.out.println("---- Inversión total ----");
		double inv = arbol.calcularInversionTotal();
        System.out.println("La inversión total en la empresa es: $" + inv);
	}	
}
