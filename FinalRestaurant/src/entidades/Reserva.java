package entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int idReserva;
    private int id_mesa;
    private String nombrePersona;
    private String dni;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean estado;

    public Reserva(int id, int id_mesa, String nombrePersona, String dni, LocalDate fecha, LocalTime hora, boolean estado) {
        this.idReserva = id;
        this.id_mesa = id_mesa;
        this.nombrePersona = nombrePersona;
        this.dni = dni;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Reserva(int id_mesa, String nombrePersona, String dni, LocalDate fecha, LocalTime hora, boolean estado) {
        this.id_mesa = id_mesa;
        this.nombrePersona = nombrePersona;
        this.dni = dni;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva {" +
                "id=" + idReserva +
                ", id_mesa=" + id_mesa +
                ", nombrePersona='" + nombrePersona + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", estado=" + (estado ? "Vigente" : "No vigente") +
                '}';
    }
}
