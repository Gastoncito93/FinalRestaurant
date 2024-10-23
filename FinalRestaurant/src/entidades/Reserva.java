package entidades;

public class Reserva {
    private int id;
    private String nombrePersona;
    private String dni;
    private String fecha;
    private String hora;
    private boolean estado;

    public Reserva(int id, String nombrePersona, String dni, String fecha, String hora, boolean estado) {
        this.id = id;
        this.nombrePersona = nombrePersona;
        this.dni = dni;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Reserva(String nombrePersona, String dni, String fecha, String hora, boolean estado) {
        this.nombrePersona = nombrePersona;
        this.dni = dni;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Reserva() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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
                "id=" + id +
                ", nombrePersona='" + nombrePersona + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", estado=" + (estado ? "Vigente" : "No vigente") +
                '}';
    }

}

