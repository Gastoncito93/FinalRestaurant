package entidades;

public class Producto {
    private int codigo;
    private String nombre;
    private int cantidadDisponible;
    private double precio;
    private String categoria; // comida, bebida sin alcohol, bebida con alcohol

    public Producto(int codigo, String nombre, int cantidadDisponible, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(String nombre, int cantidadDisponible, double precio, String categoria) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto() {
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

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return "Producto {" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }

}
