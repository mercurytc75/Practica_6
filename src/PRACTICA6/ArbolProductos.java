package PRACTICA6;

import java.util.ArrayList;
import java.util.List;

public class ArbolProductos {

	private NodoProducto raiz;
	
	public ArbolProductos() {
		raiz = null;
	}
	
	public boolean insertar(Producto p) {
		int[] contador = {0};
		raiz = insertarRec(raiz, p, contador);
		return contador[0] == 1;
	}
	
	public NodoProducto insertarRec(NodoProducto actual, Producto p, int[] contador) {
		if(actual == null) {
			contador[0] = 1;
			return new  NodoProducto(p);
		}
		
		if(p.getClave() < actual.dato.getClave()) {
			actual.izq = insertarRec(actual.izq, p, contador);
		}else if (p.getClave() > actual.dato.getClave()) {
			actual.der = insertarRec(actual.der, p, contador);
		}
		return actual;
	}
	
	public Producto buscar(int clave) {
		NodoProducto n = buscarRec(raiz, clave);
		return (n != null) ? n.dato : null;
	}
	
	private NodoProducto buscarRec(NodoProducto actual, int clave) {
		if(actual == null){
			return null;
		}
		if(clave == actual.dato.getClave()) {
			return actual;
		}else if( clave < actual.dato.getClave()) {
			return buscarRec(actual.izq, clave);
		}else {
			return buscarRec(actual.der, clave);
		}
	}

	public ResultadoOperacion vender(int clave, int cantidad) {
		NodoProducto n = buscarRec(raiz, clave);
		if(n == null) {
			return new ResultadoOperacion(false, "No existe un producto con clave " + clave);
		}
		if(cantidad <= 0) {
			return new ResultadoOperacion(false, "La cantidad debe ser mayor que cero.");
		}
		if(cantidad > n.dato.getInventario()) {
			return new ResultadoOperacion(false, "No hay suficiente inventario disponible. Disponible: " + n.dato.getInventario());
		}
		
		int nuevoInventario = n.dato.getInventario() - cantidad;
		n.dato.setInventario(nuevoInventario);
		
		double total = cantidad * n.dato.getPrecioVenta();
		return new ResultadoOperacion(true, "Venta realizada exitosamente.", n.dato, total);
	}
	
	public ResultadoOperacion comprar(int clave, int cantidad) {
		NodoProducto n = buscarRec(raiz, clave);
		if(n == null) {
			return new ResultadoOperacion(false, "No existe un producto con clave " + clave);
		}
		
		if(cantidad <= 0) {
			return new ResultadoOperacion(false, "La cantidad debe ser mayor que cero.");
		}
		
		int nuevoInventario = n.dato.getInventario() + cantidad;
		n.dato.setInventario(nuevoInventario);
		return new ResultadoOperacion(true, "Compra registrada exitosamente.", n.dato, 0);
	}
	
	public double calcularInversionTotal() {
		return inversionRec(raiz);
	}
	
	public double inversionRec(NodoProducto actual) {
		if(actual == null) {
			return 0;
		}
		double izquierda = inversionRec(actual.izq);
		double derecha = inversionRec(actual.der);
		double propio = actual.dato.getInventario() * actual.dato.getCostoUnitario();
		return izquierda + propio + derecha;
	}
	
	public void imprimirInOrden() {
		imprimirInOrdenRec(raiz);
	}
	
	private void imprimirInOrdenRec(NodoProducto actual) {
		if(actual != null) {
			imprimirInOrdenRec(actual.izq);
			System.out.println(actual.dato);
			System.out.println("---------------------");
			imprimirInOrdenRec(actual.der);
		}
	}
	
	public List<Producto> obtenerProductosOrdenados() {
		List<Producto> lista = new ArrayList<>();
		obtenerProductosOrdenadosRec(raiz, lista);
		return lista;
	}
	
	private void obtenerProductosOrdenadosRec(NodoProducto actual, List<Producto> lista) {
		if(actual != null) {
			obtenerProductosOrdenadosRec(actual.izq, lista);
			lista.add(actual.dato);
			obtenerProductosOrdenadosRec(actual.der, lista);
		}
	}
		
}
