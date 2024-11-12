package entidades;

public class Producto {
    private int idProducto;
    private String nombre;
    private int cantidad;
    private double precio;
    private String tipo;
    private boolean estado;
    private int idPedido;
    
    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros
    public Producto(int idProducto, String nombre, int cantidad, double precio, String tipo, boolean estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Producto(int idProducto, String nombre, int cantidad) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    

    public Producto(String nombre, int cantidad, double precio, String tipo, boolean estado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;
    }

    
    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    // Método toString para representar el objeto como cadena
    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", tipo='" + tipo + '\'' +
                ", estado=" + estado +
                '}';
    }
}

