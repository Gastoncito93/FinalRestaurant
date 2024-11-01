package entidades;

public class Mesa {
    private int idMesa;
    private int numero;
    private int capacidad;
    private boolean estado;
    private Integer idReserva;

    // Constructor vacío
    public Mesa() {}

    // Constructor con parámetros
    public Mesa(int idMesa, int numero, int capacidad, boolean estado, Integer idReserva) {
        this.idMesa = idMesa;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.idReserva = idReserva;
    }

    public Mesa(int numero, int capacidad, boolean estado, Integer idReserva) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.idReserva = idReserva;
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

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

 
    
    

    @Override
    public String toString() {
        return "Mesa{" +
                "idMesa=" + idMesa +
                ", numero=" + numero +
                ", capacidad=" + capacidad +
                ", estado=" + estado +
                ", idReserva=" + idReserva +
                '}';
    }
}
