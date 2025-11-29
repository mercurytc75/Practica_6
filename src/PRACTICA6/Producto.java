package PRACTICA6;

public class Producto {

	private int clave;
	private String descripcion;
	private int inventario;
	private double costoUnitario;
	private double precioVenta;	
	
	public Producto(int clave, String descripcion, int inventario, double costoUnitario, double precioVenta) {
		this.clave = clave;
		this.descripcion = descripcion;
		this.inventario = inventario;
		this.costoUnitario = costoUnitario;
		this.precioVenta = precioVenta;
	}
	
	

	public void setInventario(int inventario) {
		this.inventario = inventario;
	}



	public int getClave() {
		return clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getInventario() {
		return inventario;
	}

	public double getCostoUnitario() {
		return costoUnitario;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	@Override
	public String toString() {
		return "Clave: " + clave +
		"\nDescripcion: " + descripcion +
		"\nInventario: " + inventario +
		"\nCosto unitario: " + costoUnitario +
		"\nPrecio venta: " + precioVenta;  
	}
	
}
