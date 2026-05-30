package logica;
import java.io.Serializable;

public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
    private int codigo;
    private String nombre;
    private double existencias;
    private String unidad;
    private double precio;
    
    public Producto(int codigo, String nombre, double existencias, String unidad, double precio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.existencias = existencias;
		this.unidad = unidad;
		this.precio = precio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getExistencias() {
		return existencias;
	}

	public void setExistencias(double existencias) {
		this.existencias = existencias;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
    
    public void actualizarDatos(String nombre, double existencias, String unidad, double precio) {
        this.nombre = nombre;
        this.existencias = existencias;
        this.unidad = unidad;
        this.precio = precio;
    }
    
    public boolean hayExistencias(double cantidad) {
        return cantidad <= existencias;
    }

    public void disminuirExistencias(double cantidad) {
        existencias -= cantidad;
    }

    public void aumentarExistencias(double cantidad) {
        existencias += cantidad;
    }
    
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
    
}
