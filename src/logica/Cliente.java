package logica;

import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String email;
	private Map<Integer, OrdenCompra> ordenes;
	

	public Cliente(String id, String nombre, String email) {
	    this.id = id;
	    this.nombre = nombre;
	    this.email = email;
	    this.ordenes = new TreeMap<>();
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}

	public Map<Integer, OrdenCompra> getOrdenes() {
		return ordenes;
	}
	
	public void agregarOrden(OrdenCompra orden) {
		ordenes.put(orden.getNumero(), orden);
	}
	
	public void borrarOrden(OrdenCompra orden) {
		ordenes.remove(orden.getNumero());
	}
	
	public double calcularMontoPendiente() {
	    double total = 0;

	    for (OrdenCompra orden : ordenes.values()) {
	        if (orden.getEstado() == EstadoOrden.PENDIENTE) {
	            total += orden.calcularTotal();
	        }
	    }

	    return total;
	}

	@Override
	public String toString() {
	    return id + " - " + nombre;
	}
	
}
