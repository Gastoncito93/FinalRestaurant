package entidades;

public class Mesero {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String usuario;
    private String contraseña;

    public Mesero(int id, String nombre, String apellido, String dni, String usuario, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.usuario = usuario;
        this.contraseña = contraseña;
    } 

    public Mesero(String nombre, String apellido, String dni, String usuario, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Mesero() {
    }

    public Mesero(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

    public String getTelefono() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTelefono(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
