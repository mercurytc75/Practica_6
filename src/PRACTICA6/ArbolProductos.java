package PRACTICA6;

public class ArbolProductos {

	private NodoProducto raiz;
	
	public ArbolProductos() {
		raiz = null;
	}
	
	public void insertar(Producto p) {
		raiz = insertarRec(raiz, p);
	}
	
	public NodoProducto insertarRec(NodoProducto actual, Producto p) {
		if(actual == null) {
			return new  NodoProducto(p);
		}
		
		if(p.getClave() < actual.dato.getClave()) {
			actual.izq = insertarRec(actual.izq, p);
		}else if (p.getClave() > actual.dato.getClave()) {
			actual.der = insertarRec(actual.der, p);
		}else {
			System.out.println(">> la clave " + p.getClave() + "ya existe. No se inserta. " );
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

	public  double vender(int clave, int cantidad) {
		NodoProducto n = buscarRec(raiz, clave);
		if(n == null) {
			System.out.println(">> No existe un producto con clave " + clave);
			return -1;
		}
		if(cantidad <= 0) {
			System.out.println(">> la cantidad dede ser mayor que cero. ");
			return -1;
		}
		if(cantidad > n.dato.getInventario()) {
			System.out.println(">> No hay suficiente inventario. disponible.");
			return -1;
		}
		
		int nuevoInventario = n.dato.getInventario() - cantidad;
		n.dato.setInventario(nuevoInventario);
		
		double total = cantidad * n.dato.getPrecioVenta();
		return total;
	}
	
	public boolean comprar(int clave, int cantidad) {
		NodoProducto n = buscarRec(raiz, clave);
		if(n == null) {
			System.out.println(">> No existe un producto con clave " + clave);
			return false;
		}
		
		if(cantidad <= 0) {
			System.out.println(">> la cantidad debe ser mayor que cero. ");
			return false;
		}
		
		int nuevoInventario = n.dato.getInventario() + cantidad;
		n.dato.setInventario(nuevoInventario);
		return true;
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
		
}
