package PRACTICA6;

public class Producto {

	private int clave;
	private String descripcion;
	private int inventario;
	private double costoUnitario;
	private double precioVenta;	
	
	public Producto(int clave, String descripcion, int inventario, double costoUnitario, double precioVenta) {
		if (inventario < 0) {
			throw new IllegalArgumentException("El inventario no puede ser negativo.");
		}
		if (costoUnitario < 0) {
			throw new IllegalArgumentException("El costo unitario no puede ser negativo.");
		}
		if (precioVenta <= costoUnitario) {
			throw new IllegalArgumentException("El precio de venta debe ser mayor al costo unitario.");
		}
		
		this.clave = clave;
		this.descripcion = descripcion;
		this.inventario = inventario;
		this.costoUnitario = costoUnitario;
		this.precioVenta = precioVenta;
	}
	
	

	public void setInventario(int inventario) {
		if (inventario < 0) {
			throw new IllegalArgumentException("El inventario no puede ser negativo.");
		}
		this.inventario = inventario;
	}
	
	public void setCostoUnitario(double costoUnitario) {
		if (costoUnitario < 0) {
			throw new IllegalArgumentException("El costo unitario no puede ser negativo.");
		}
		this.costoUnitario = costoUnitario;
	}
	
	public void setPrecioVenta(double precioVenta) {
		if (precioVenta <= this.costoUnitario) {
			throw new IllegalArgumentException("El precio de venta debe ser mayor al costo unitario.");
		}
		this.precioVenta = precioVenta;
	}
	
	public double calcularMargen() {
		return precioVenta - costoUnitario;
	}
	
	public double calcularPorcentajeMargen() {
		return ((precioVenta - costoUnitario) / costoUnitario) * 100;
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
