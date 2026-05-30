package control;

import logica.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ControladoraWallRose implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String ARCHIVO_DATOS = "DatosWallRose.bin";
	
	private static ControladoraWallRose instancia;

    private int consecutivoOrden;
    private int consecutivoProducto;

    private Map<String, Cliente> clientes;
    private Map<Integer, Producto> productos;
    private Map<Integer, OrdenCompra> ordenes;

    private ControladoraWallRose() {
        consecutivoOrden = 1;
        consecutivoProducto = 1;

        clientes = new TreeMap<>();
        productos = new TreeMap<>();
        ordenes = new TreeMap<>();
        
        // TEMPORAL: Cargar datos de prueba
        cargarDatosPrueba();
    }

    public static ControladoraWallRose obtenerInstancia() {
        if (instancia == null) {
            instancia = new ControladoraWallRose();
        }
        return instancia;
    }

    private Cliente obtenerClienteObligatorio(String idCliente) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe el cliente.");
        }
        return cliente;
    }

    private boolean emailValido(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
    
    public List<Cliente> obtenerListadoClientes() {
        return new ArrayList<>(clientes.values());
    }

    public Cliente obtenerCliente(String idCliente) {
        return clientes.get(idCliente);
    }

    public void crearCliente(String idCliente, String nombre, String email) {
        if (clientes.containsKey(idCliente)) {
            throw new IllegalArgumentException("Ya existe un cliente con ese ID.");
        }

        if (!emailValido(email)) {
            throw new IllegalArgumentException("El email no es válido.");
        }

        Cliente cliente = new Cliente(idCliente, nombre, email);
        clientes.put(idCliente, cliente);
    }

    public void actualizarCliente(String idCliente, String nombre, String email) {
        Cliente cliente = obtenerClienteObligatorio(idCliente);

        if (!emailValido(email)) {
            throw new IllegalArgumentException("El email no es válido.");
        }

        cliente.setNombre(nombre);
        cliente.setEmail(email);
    }

    public void borrarCliente(String idCliente) {
        Cliente cliente = obtenerClienteObligatorio(idCliente);

        for (OrdenCompra orden : cliente.getOrdenes().values()) {
            ordenes.remove(orden.getNumero());
        }

        clientes.remove(idCliente);
    }
    
    private Producto obtenerProductoObligatorio(int codigoProducto) {
        Producto producto = productos.get(codigoProducto);
        if (producto == null) {
            throw new IllegalArgumentException("No existe el producto.");
        }
        return producto;
    }

    private void validarProducto(double existencias, double precio) {
        if (existencias < 0) {
            throw new IllegalArgumentException("Las existencias no pueden ser negativas.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
    }

    private boolean productoEstaEnAlgunaOrden(Producto producto) {
        for (OrdenCompra orden : ordenes.values()) {
            for (LineaOrden linea : orden.getLineas()) {
                if (linea.getProducto().getCodigo() == producto.getCodigo()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public List<Producto> obtenerListadoProductos() {
        return new ArrayList<>(productos.values());
    }

    public int crearProducto(String nombre, double existencias, String unidad, double precio) {
        validarProducto(existencias, precio);

        int codigo = consecutivoProducto++;
        Producto producto = new Producto(codigo, nombre, existencias, unidad, precio);
        productos.put(codigo, producto);

        return codigo;
    }

    public Producto obtenerProducto(int codigoProducto) {
        return productos.get(codigoProducto);
    }

    public void actualizarProducto(int codigoProducto, String nombre, double existencias, String unidad, double precio) {
        validarProducto(existencias, precio);

        Producto producto = obtenerProductoObligatorio(codigoProducto);
        producto.actualizarDatos(nombre, existencias, unidad, precio);
    }

    public void borrarProducto(int codigoProducto) {
        Producto producto = obtenerProductoObligatorio(codigoProducto);

        if (productoEstaEnAlgunaOrden(producto)) {
            throw new IllegalArgumentException("No se puede borrar el producto porque está en una orden.");
        }

        productos.remove(codigoProducto);
    }
    
    public List<OrdenCompra> obtenerListadoOrdenes() {
        return new ArrayList<>(ordenes.values());
    }

    public int crearOrdenVacia(String idCliente) {
        Cliente cliente = obtenerClienteObligatorio(idCliente);

        int numero = consecutivoOrden++;
        OrdenCompra orden = new OrdenCompra(numero, cliente);

        ordenes.put(numero, orden);
        cliente.agregarOrden(orden);

        return numero;
    }

    public OrdenCompra obtenerOrdenCompra(int numeroOrden) {
        return ordenes.get(numeroOrden);
    }

    public List<LineaOrden> obtenerLineasOrden(int numeroOrden) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);
        return orden.getLineas();
    }

    private OrdenCompra obtenerOrdenObligatoria(int numeroOrden) {
        OrdenCompra orden = ordenes.get(numeroOrden);
        if (orden == null) {
            throw new IllegalArgumentException("No existe la orden.");
        }
        return orden;
    }
    
    public List<OrdenCompra> obtenerListadoOrdenesCliente(String idCliente) {
        Cliente cliente = obtenerClienteObligatorio(idCliente);
        return new ArrayList<>(cliente.getOrdenes().values());
    }

    public List<OrdenCompra> obtenerListadoOrdenesIniciadasCliente(String idCliente) {
        return obtenerOrdenesClientePorEstado(idCliente, EstadoOrden.INICIADA);
    }

    public List<OrdenCompra> obtenerListadoOrdenesPendientesCliente(String idCliente) {
        return obtenerOrdenesClientePorEstado(idCliente, EstadoOrden.PENDIENTE);
    }

    public List<OrdenCompra> obtenerListadoOrdenesTerminadasCliente(String idCliente) {
        return obtenerOrdenesClientePorEstado(idCliente, EstadoOrden.TERMINADA);
    }

    private List<OrdenCompra> obtenerOrdenesClientePorEstado(String idCliente, EstadoOrden estado) {
        Cliente cliente = obtenerClienteObligatorio(idCliente);
        List<OrdenCompra> resultado = new ArrayList<>();

        for (OrdenCompra orden : cliente.getOrdenes().values()) {
            if (orden.getEstado() == estado) {
                resultado.add(orden);
            }
        }

        return resultado;
    }
    
    private void validarOrdenEditable(OrdenCompra orden) {
        if (orden.getEstado() != EstadoOrden.INICIADA) {
            throw new IllegalArgumentException("Solo se pueden modificar líneas de órdenes iniciadas.");
        }
    }

    private void validarCantidad(Producto producto, double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        if (!producto.hayExistencias(cantidad)) {
            throw new IllegalArgumentException("No hay existencias suficientes.");
        }
    }
    
    public void agregarLineaOrden(int numeroOrden, int codigoProducto, double cantidad) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);
        Producto producto = obtenerProductoObligatorio(codigoProducto);

        validarOrdenEditable(orden);
        validarCantidad(producto, cantidad);

        LineaOrden linea = new LineaOrden(producto, cantidad);
        orden.agregarLinea(linea);
    }

    public void actualizarLineaOrden(int numeroOrden, int numeroLinea, int codigoProducto, double cantidad) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);
        Producto producto = obtenerProductoObligatorio(codigoProducto);

        validarOrdenEditable(orden);
        validarCantidad(producto, cantidad);

        orden.actualizarLinea(numeroLinea, producto, cantidad);
    }

    public void borrarLineaOrden(int numeroOrden, int numeroLinea) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);

        validarOrdenEditable(orden);

        orden.borrarLinea(numeroLinea);
    }
    
    public void establecerOrdenPendiente(int numeroOrden) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);

        if (orden.getEstado() != EstadoOrden.INICIADA) {
            throw new IllegalArgumentException("Solo una orden iniciada puede pasar a pendiente.");
        }

        for (LineaOrden linea : orden.getLineas()) {
            Producto producto = linea.getProducto();
            if (!producto.hayExistencias(linea.getCantidad())) {
                throw new IllegalArgumentException("No hay existencias suficientes para " + producto.getNombre());
            }
        }

        for (LineaOrden linea : orden.getLineas()) {
            linea.getProducto().disminuirExistencias(linea.getCantidad());
        }

        orden.setEstado(EstadoOrden.PENDIENTE);
    }

    public void establecerOrdenTerminada(int numeroOrden) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);

        if (orden.getEstado() != EstadoOrden.PENDIENTE) {
            throw new IllegalArgumentException("Solo una orden pendiente puede terminarse.");
        }

        orden.setEstado(EstadoOrden.TERMINADA);
    }
    
    public double obtenerMontoTotalPendiente() {
        double total = 0;

        for (OrdenCompra orden : ordenes.values()) {
            if (orden.getEstado() == EstadoOrden.PENDIENTE) {
                total += orden.calcularTotal();
            }
        }

        return total;
    }

    public void borrarOrden(int numeroOrden) {
        OrdenCompra orden = obtenerOrdenObligatoria(numeroOrden);

        Cliente cliente = orden.getCliente();
        cliente.borrarOrden(orden);

        ordenes.remove(numeroOrden);
    }
    
    public static void guardarDatos() throws IOException {
    	FileOutputStream file = new FileOutputStream(ARCHIVO_DATOS);
    	ObjectOutputStream stream = new ObjectOutputStream(file);
    	stream.writeObject(obtenerInstancia());
    	stream.close();
    	file.close();
    	}
    
    public static void cargarDatos() throws IOException, ClassNotFoundException {
    	FileInputStream file = new FileInputStream(ARCHIVO_DATOS);
    	ObjectInputStream stream = new ObjectInputStream(file);
    	instancia = (ControladoraWallRose) stream.readObject();
    	stream.close();
    	file.close();
    	}
    
    // TEMPORAL: Método para cargar datos de prueba al iniciar la aplicación
    private void cargarDatosPrueba() {
        try {
            crearCliente("1-1111-1111", "Dmitri Volkov", "dmitri.volkov@wallrose.com");
            crearCliente("2-2222-2222", "Anastasia Morozova", "anastasia.morozova@wallrose.com");
            crearCliente("3-3333-3333", "Nikolai Sokolov", "nikolai.sokolov@wallrose.com");
            crearCliente("4-4444-4444", "Irina Petrova", "irina.petrova@wallrose.com");

            int codigoRacion = crearProducto("Ración militar", 30.0, "unidad", 1200.0);
            int codigoCaja = crearProducto("Caja de cartón táctica", 15.0, "unidad", 750.0);
            int codigoCodec = crearProducto("Codec portátil", 5.0, "unidad", 8500.0);
            int codigoCigarros = crearProducto("Cigarros marca Phantom", 20.0, "paquete", 950.0);
            int codigoBandana = crearProducto("Bandana infinita", 3.0, "unidad", 12000.0);
            int codigoNanoMaquinas = crearProducto("Nanomáquinas", 50.0, "dosis", 3000.0);
            int codigoCamuflaje = crearProducto("Traje de camuflaje óptico", 4.0, "unidad", 15000.0);

            int orden1 = crearOrdenVacia("1-1111-1111");
            agregarLineaOrden(orden1, codigoRacion, 3.0);
            agregarLineaOrden(orden1, codigoCaja, 1.0);
            establecerOrdenPendiente(orden1);

            int orden2 = crearOrdenVacia("2-2222-2222");
            agregarLineaOrden(orden2, codigoCodec, 1.0);
            agregarLineaOrden(orden2, codigoCigarros, 2.0);

            int orden3 = crearOrdenVacia("3-3333-3333");
            agregarLineaOrden(orden3, codigoBandana, 1.0);
            agregarLineaOrden(orden3, codigoNanoMaquinas, 5.0);
            establecerOrdenPendiente(orden3);

            int orden4 = crearOrdenVacia("4-4444-4444");
            agregarLineaOrden(orden4, codigoCamuflaje, 1.0);
            agregarLineaOrden(orden4, codigoRacion, 2.0);
            establecerOrdenTerminada(orden4);

        } catch (Exception e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }
    
}