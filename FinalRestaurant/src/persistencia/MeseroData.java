package persistencia;

import entidades.Mesero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeseroData {

    private Connection connection;

    public MeseroData(Connection connection) {
        this.connection = connection;
    }

    // Agregar un mesero a la base de datos
    public void agregarMesero(Mesero mesero) {
        String sql = "INSERT INTO mesero (nombre, apellido, dni, usuario, contraseña) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, mesero.getNombre());
            ps.setString(2, mesero.getApellido());
            ps.setString(3, mesero.getDni());
            ps.setString(4, mesero.getUsuario());
            ps.setString(5, mesero.getContraseña());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                mesero.setId(rs.getInt(1));
            }
            ps.close();
            System.out.println("Mesero agregado: " + mesero);
        } catch (SQLException e) {
            System.out.println("Error al agregar el mesero: " + e.getMessage());
        }
    }

    // Obtener todos los meseros
    public List<Mesero> obtenerMeseros() {
        List<Mesero> meseros = new ArrayList<>();
        String sql = "SELECT * FROM mesero";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Mesero mesero = new Mesero();
                mesero.setId(rs.getInt("id"));
                mesero.setNombre(rs.getString("nombre"));
                mesero.setApellido(rs.getString("apellido"));
                mesero.setDni(rs.getString("dni"));
                mesero.setUsuario(rs.getString("usuario"));
                mesero.setContraseña(rs.getString("contraseña"));
                meseros.add(mesero);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los meseros: " + e.getMessage());
        }
        return meseros;
    }

    // Buscar un mesero por ID
    public Mesero buscarMesero(int id) {
        Mesero mesero = null;
        String sql = "SELECT * FROM mesero WHERE id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                mesero = new Mesero();
                mesero.setId(rs.getInt("id"));
                mesero.setNombre(rs.getString("nombre"));
                mesero.setApellido(rs.getString("apellido"));
                mesero.setDni(rs.getString("dni"));
                mesero.setUsuario(rs.getString("usuario"));
                mesero.setContraseña(rs.getString("contraseña"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar el mesero: " + e.getMessage());
        }
        return mesero;
    }

    // Actualizar un mesero
    public void actualizarMesero(Mesero mesero) {
        String sql = "UPDATE mesero SET nombre = ?, apellido = ?, dni = ?, usuario = ?, contraseña = ? WHERE id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mesero.getNombre());
            ps.setString(2, mesero.getApellido());
            ps.setString(3, mesero.getDni());
            ps.setString(4, mesero.getUsuario());
            ps.setString(5, mesero.getContraseña());
            ps.setInt(6, mesero.getId());

            ps.executeUpdate();
            ps.close();
            System.out.println("Mesero actualizado: " + mesero);
        } catch (SQLException e) {
            System.out.println("Error al actualizar el mesero: " + e.getMessage());
        }
    }

    // Eliminar un mesero
    public void eliminarMesero(int id) {
        String sql = "DELETE FROM mesero WHERE id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("Mesero eliminado con ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error al eliminar el mesero: " + e.getMessage());
        }
    }
}
