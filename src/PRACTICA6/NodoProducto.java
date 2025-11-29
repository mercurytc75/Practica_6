package PRACTICA6;

public class NodoProducto {

	Producto dato;
	NodoProducto izq;
	NodoProducto der;
	
	public NodoProducto(Producto dato) {
		this.dato = dato;
		this.izq = null;
		this.der = null;
	}

}
