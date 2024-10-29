package entidades;

public class Mesero {
    private int idMesero;
    private String nombre;
    private String apellido;
    private String dni;
    private boolean estado;
    private String usuario;
    private String contraseña;

    public Mesero() {
    }

    public Mesero(String nombre, String apellido, String dni, boolean estado, String usuario, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.estado = estado;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Mesero(int idMesero, String nombre, String apellido, String dni, boolean estado, String usuario, String contraseña) {
        this.idMesero = idMesero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.estado = estado;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public int getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(int idMesero) {
        this.idMesero = idMesero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    
    
    
    
    @Override
    public String toString() {
        return "Mesero {" +
                "id=" + idMesero +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", estado='" + estado + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

}
