package PRACTICA6;

public class ResultadoOperacion {
	public boolean exito;
	public String mensaje;
	public Producto producto;
	public double valor;
	
	public ResultadoOperacion(boolean exito, String mensaje) {
		this.exito = exito;
		this.mensaje = mensaje;
	}
	
	public ResultadoOperacion(boolean exito, String mensaje, Producto producto, double valor) {
		this.exito = exito;
		this.mensaje = mensaje;
		this.producto = producto;
		this.valor = valor;
	}
}
