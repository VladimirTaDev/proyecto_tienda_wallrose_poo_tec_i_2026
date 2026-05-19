package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {
	private int numero;
	private LocalDateTime fecha;
	private static double IV = 0.13;
	private Cliente cliente;
	private EstadoOrden estado;
	private List<LineaOrden> lineas;

	public OrdenCompra(int numero, Cliente cliente) {
		this.numero = numero;
		this.cliente = cliente;
		this.fecha = LocalDateTime.now();
        this.estado = EstadoOrden.INICIADA;
        this.lineas = new ArrayList<>();
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<LineaOrden> getLineas() {
		return lineas;
	}
	
	public double calcularMonto() {
		double total = 0;
		for (LineaOrden linea : lineas) {
			total += linea.calcularCosto();
		}
		
		return total;
	}
	
	public double calcularMontoImpuesto() {
		double impuesto = calcularMonto() * IV;
		return impuesto;
	}
	
	public double calcularTotal() {
        return calcularMonto() + calcularMontoImpuesto();
    }

	public void agregarLinea(LineaOrden linea) {
		this.lineas.add(linea);
	}
	
	public void borrarLinea(int numeroLinea) {
	    if (numeroLinea < 0 || numeroLinea >= lineas.size()) {
	        throw new IllegalArgumentException("Número de línea no válido.");
	    }

	    lineas.remove(numeroLinea);
	}
	
	public void actualizarLinea(int numeroLinea, Producto producto, double cantidad) {
	    if (numeroLinea < 0 || numeroLinea >= lineas.size()) {
	        throw new IllegalArgumentException("Número de línea no válido.");
	    }

	    lineas.get(numeroLinea).actualizar(producto, cantidad);
	}
	
	public int cantidadLineas() {
	    return lineas.size();
	}

	public LineaOrden getLinea(int numeroLinea) {
	    return lineas.get(numeroLinea);
	}
}
