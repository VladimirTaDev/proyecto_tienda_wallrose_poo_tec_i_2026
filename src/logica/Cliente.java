package logica;

import java.util.Map;
import java.util.TreeMap;

public class Cliente {
	private String id;
	private String email;
	private String nombre;
	private Map<Integer, OrdenCompra> ordenes;
	

	public Cliente(String id, String email, String nombre) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
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
	
}
