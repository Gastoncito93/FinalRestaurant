package entidades;

public class Mesa {
    private int id;
    private int numero;
    private int capacidad;
    private String estado; // ocupada, libre, reservada
    private Reserva reserva; // Relación con Reserva

    public Mesa(int id, int numero, int capacidad, String estado, Reserva reserva) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.reserva = reserva;
    }

    public Mesa(int numero, int capacidad, String estado, Reserva reserva) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.reserva = reserva;
    }

    public Mesa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
        return "Mesa {" +
                "id=" + id +
                ", numero=" + numero +
                ", capacidad=" + capacidad +
                ", estado='" + estado + '\'' +
                ", reserva=" + (reserva != null ? reserva.toString() : "Sin reserva") +
                '}';
    }
}

