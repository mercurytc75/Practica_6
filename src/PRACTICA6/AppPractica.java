package PRACTICA6;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class AppPractica {
	private static final Scanner sc = new Scanner(System.in);
	private static final ArbolProductos arbol = new ArbolProductos();
	private static final NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.of("es", "MX"));

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String rutaArchivo = "productos.csv";
		
		LectorProductosCSV.cargarDesdeArchivo(rutaArchivo, arbol);
		
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
            case 7 -> guardarCambios();
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
	        System.out.println("7. Guardar cambios en archivo CSV");
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
	        int clave = leerEntero("Clave (única): ");
	        
	        if (arbol.buscar(clave) != null) {
	            System.out.println(">> Ya existe un producto con la clave " + clave);
	            return;
	        }
	        
	        System.out.print("Descripción: ");
	        String descripcion = sc.nextLine();
	        int inventario = leerEntero("Inventario inicial: ");
	        double costo = leerDouble("Costo unitario: ");
	        double precio = leerDouble("Precio de venta: ");

	        try {
	        	Producto p = new Producto(clave, descripcion, inventario, costo, precio);
	        	if (arbol.insertar(p)) {
	        		System.out.println("✓ Producto agregado exitosamente.");
	        	} else {
	        		System.out.println(">> No se pudo agregar el producto.");
	        	}
	        } catch (IllegalArgumentException e) {
	        	System.out.println(">> " + e.getMessage());
	        }
	    }	private static void buscarProducto() {
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

	        ResultadoOperacion resultado = arbol.vender(clave, cantidad);
	        if (resultado.exito) {
	            System.out.println("✓ " + resultado.mensaje);
	            System.out.println("Total a pagar: " + formatoMoneda.format(resultado.valor));
	            System.out.println("Inventario restante: " + resultado.producto.getInventario());
	        } else {
	            System.out.println(">> " + resultado.mensaje);
	        }
	    }	    private static void realizarCompra() {
	        System.out.println("---- Compra de producto ----");
	        int clave = leerEntero("Clave del producto: ");
	        int cantidad = leerEntero("Cantidad a comprar: ");

	        ResultadoOperacion resultado = arbol.comprar(clave, cantidad);
	        if (resultado.exito) {
	            System.out.println("✓ " + resultado.mensaje);
	            System.out.println("Nuevo inventario: " + resultado.producto.getInventario());
	        } else {
	            System.out.println(">> " + resultado.mensaje);
	        }
	    }	private static void imprimirInversion() {
		System.out.println("---- Inversión total ----");
		double inv = arbol.calcularInversionTotal();
        System.out.println("La inversión total en la empresa es: " + formatoMoneda.format(inv));
	}
	
	private static void guardarCambios() {
        System.out.println("---- Guardar cambios ----");
        String rutaArchivo = "productos.csv";
        LectorProductosCSV.guardarEnArchivo(rutaArchivo, arbol);
    }
	
}
