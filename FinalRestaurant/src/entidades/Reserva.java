package entidades;

import java.sql.Time;
import java.util.Date;

public class Reserva {
    private int id;
    private String nombreCliente;
    private String dniCliente;
    private Date fechaReserva;
    private Time horaReserva;
    private String estado;
    private Mesa mesa;  // Relación con mesa

    // Constructor vacío (por defecto)
    public Reserva() {
    }

    // Constructor con todos los parámetros, si lo necesitas
    public Reserva(int id, Mesa mesa, String nombreCliente, String dniCliente, Date fechaReserva, Time horaReserva, String estado) {
        this.id = id;
        this.mesa = mesa;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.estado = estado;
    }

    // Getters y setters

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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Time getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = horaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}


