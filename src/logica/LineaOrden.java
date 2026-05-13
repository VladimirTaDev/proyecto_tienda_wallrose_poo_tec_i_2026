package logica;

public class LineaOrden {
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
    
    
}
