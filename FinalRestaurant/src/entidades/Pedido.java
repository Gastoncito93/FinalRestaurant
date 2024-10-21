package entidades;

import java.util.List;

public class Pedido {
    private int id;
    private Mesa mesa;
    private Mesero mesero;
    private List<Producto> productos;
    private double total;
    private String fechaHora;

    public Pedido(int id, Mesa mesa, Mesero mesero, List<Producto> productos, String fechaHora) {
        this.id = id;
        this.mesa = mesa;
        this.mesero = mesero;
        this.productos = productos;
        this.fechaHora = fechaHora;
        calcularTotal();
    }

    public void calcularTotal() {
        total = productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public Pedido(Mesa mesa, Mesero mesero, List<Producto> productos, double total, String fechaHora) {
        this.mesa = mesa;
        this.mesero = mesero;
        this.productos = productos;
        this.total = total;
        this.fechaHora = fechaHora;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    @Override
    public String toString() {
        return "Pedido {" +
                "id=" + id +
                ", mesa=" + mesa.getNumero() +
                ", mesero=" + mesero.getNombre() + " " + mesero.getApellido() +
                ", productos=" + productos +
                ", total=" + total +
                ", fechaHora='" + fechaHora + '\'' +
                '}';
    }

}
