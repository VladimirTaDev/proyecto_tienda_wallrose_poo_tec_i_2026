package logica;

import java.io.Serializable;


public class LineaOrden implements Serializable {
	private static final long serialVersionUID = 1L;
    private Producto producto;
    private double cantidad;

    public LineaOrden(Producto producto, double cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public double calcularCosto() {
        return cantidad * producto.getPrecio();
    }
	
	public void actualizar(Producto producto, double cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
}
