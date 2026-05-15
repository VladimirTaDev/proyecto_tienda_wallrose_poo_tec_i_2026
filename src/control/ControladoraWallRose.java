package control;

import logica.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ControladoraWallRose {
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
    
}