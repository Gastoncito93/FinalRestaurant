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

    // Método para agregar un nuevo mesero
    public void agregarMesero(Mesero mesero) {
        String sql = "INSERT INTO Mesero (nombre, apellido, dni, estado, usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, mesero.getNombre());
            pstmt.setString(2, mesero.getApellido());
            pstmt.setString(3, mesero.getDni());
            pstmt.setBoolean(4, mesero.isEstado());
            pstmt.setString(5, mesero.getUsuario());
            pstmt.setString(6, mesero.getContraseña());
            
            pstmt.executeUpdate();
            
            // Obtener el id generado
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                mesero.setIdMesero(rs.getInt(1));
                System.out.println("Mesero agregado: " + mesero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar un mesero por ID
    public Mesero buscarMesero(int idMesero) {
        String sql = "SELECT * FROM Mesero WHERE id_mesero = ?";
        Mesero mesero = null;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idMesero);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mesero = new Mesero(
                    rs.getInt("id_mesero"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getBoolean("estado"),
                    rs.getString("usuario"),
                    rs.getString("contraseña")
                );
                System.out.println("Mesero encontrado: " + mesero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesero;
    }
    
    public List<Mesero> obtenerMeseros() {
    List<Mesero> meseros = new ArrayList<>();
    String sql = "SELECT * FROM Mesero"; // Asegúrate de que la tabla se llama 'Mesero'

    try (PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Mesero mesero = new Mesero(
                rs.getInt("id_mesero"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getBoolean("estado"),
                rs.getString("usuario"),
                rs.getString("contraseña")
            );
            meseros.add(mesero);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return meseros;
}


    // Método para actualizar un mesero
    public void actualizarMesero(Mesero mesero) {
        String sql = "UPDATE Mesero SET nombre = ?, apellido = ?, dni = ?, estado = ?, usuario = ?, contraseña = ? WHERE id_mesero = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mesero.getNombre());
            pstmt.setString(2, mesero.getApellido());
            pstmt.setString(3, mesero.getDni());
            pstmt.setBoolean(4, mesero.isEstado());
            pstmt.setString(5, mesero.getUsuario());
            pstmt.setString(6, mesero.getContraseña());
            pstmt.setInt(7, mesero.getIdMesero());
            pstmt.executeUpdate();
            System.out.println("Mesero actualizado: " + mesero);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un mesero
    public void eliminarMesero(int idMesero) {
        String sql = "DELETE FROM Mesero WHERE id_mesero = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idMesero);
            pstmt.executeUpdate();
            System.out.println("Mesero eliminado con ID: " + idMesero);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean esDuplicado(String dni, String usuario, int idMesero) {
    String sql = "SELECT COUNT(*) FROM mesero WHERE (dni = ? OR usuario = ?) AND id_mesero != ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, dni);
        pstmt.setString(2, usuario);
        pstmt.setInt(3, idMesero);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    // Método para listar todos los meseros
    public List<Mesero> listarMeseros() {
        List<Mesero> meseros = new ArrayList<>();
        String sql = "SELECT * FROM Mesero";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mesero mesero = new Mesero(
                    rs.getInt("id_mesero"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getBoolean("estado"),
                    rs.getString("usuario"),
                    rs.getString("contraseña")
                );
                meseros.add(mesero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meseros;
    }
}
