package PRACTICA6;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class lectorproductosCSV {

	public static void cargarDesdeArchivo(String ruta, ArbolProductos arbol ) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(ruta));
			String linea;
			
			while((linea = br.readLine()) != null) {
				if(linea.trim().isEmpty()) {
					continue;
				}
				String[] partes = linea.split(",");
				
				if(partes.length < 5) {
					System.out.println(">> linea invalida en archivo: " + linea);
					continue;
				}
				
				try {
					
					int clave = Integer.parseInt(partes[0].trim());
	                String descripcion = partes[1].trim();
	            	int inventario = Integer.parseInt(partes[2].trim());
	                double costoUnitario = Double.parseDouble(partes[3].trim());
	                double precioVenta = Double.parseDouble(partes[4].trim());
	                
	                Producto p = new Producto(clave, descripcion, inventario, costoUnitario, precioVenta);
	                
	                arbol.insertar(p);
				}catch(NumberFormatException e) {
					System.out.println(">> no se puo convertir la linea " + linea);
				}
			}
			System.out.println(">> Datos cargados desde: " + ruta);
		}catch(IOException e) {
			System.out.println(">> ERROR al leer el archivo: " + e.getMessage());
		}finally {
			if(br != null) {
				try { br.close();}catch(IOException e) { }
			}
		}
    }
}
