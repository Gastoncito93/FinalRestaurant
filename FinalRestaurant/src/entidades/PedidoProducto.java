package entidades;

public class PedidoProducto {
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private double precio;
    private boolean estado;

    public PedidoProducto(int idPedido, int idProducto, int cantidad, double estado) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.estado= false;
    }

    public PedidoProducto() {
    }
    
    

    // Getters y setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PedidoProducto{" 
                + "idPedido=" + idPedido 
                + ", idProducto=" + idProducto 
                + ", cantidad=" + cantidad 
                + ", estado=" + estado 
                + '}';
    }


}
