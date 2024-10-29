package entidades;

public class Mesa {
    private int idMesa;
    private int numero;
    private int capacidad;
    private boolean estado;
    private Reserva reserva;

    // Constructor vacío
    public Mesa() {}

    // Constructor con parámetros
    public Mesa(int idMesa, int numero, int capacidad, boolean estado, Reserva reserva) {
        this.idMesa = idMesa;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.reserva = reserva;
    }

    public Mesa(int numero, int capacidad, boolean estado, Reserva reserva) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.reserva = reserva;
    }

    
    
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
 
    
    

    @Override
    public String toString() {
        return "Mesa{" +
                "idMesa=" + idMesa +
                ", numero=" + numero +
                ", capacidad=" + capacidad +
                ", estado=" + estado +
                ", idReserva=" + reserva +
                '}';
    }
}
