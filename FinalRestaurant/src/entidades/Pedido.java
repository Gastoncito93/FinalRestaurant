package entidades;

import java.time.LocalDateTime;

public class Pedido {
    private int idPedido;
    private int idMesa;
    private int idMesero;
    private LocalDateTime fecha; // Cambiamos a LocalDateTime para manejar fechas
    private boolean estado; // Estado como booleano

    // Constructor por defecto
    public Pedido() {
    }

    // Constructor con todos los atributos
    public Pedido(int idMesa, int idMesero, LocalDateTime fecha, boolean estado) {
        this.idMesa = idMesa;
        this.idMesero = idMesero;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(int idMesero) {
        this.idMesero = idMesero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido {" +
                "idPedido=" + idPedido +
                ", idMesa=" + idMesa +
                ", idMesero=" + idMesero +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }
}
