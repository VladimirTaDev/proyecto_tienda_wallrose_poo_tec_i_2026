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
}