package Persistencia;


import entidades.Mesero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeseroData {
    private Connection connection;

    public MeseroData(Connection connection) {
        this.connection = connection;
    }

    public void guardarMesero(Mesero mesero) {
        String sql = "INSERT INTO mesero (nombre, dni, telefono) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mesero.getNombre());
            ps.setString(2, mesero.getDni());
            ps.setString(3, mesero.getTelefono());
            ps.executeUpdate();
            System.out.println("Mesero guardado.");
        } catch (SQLException e) {
            System.out.println("Error al guardar mesero: " + e.getMessage());
        }
    }

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
                mesero.setDni(rs.getString("dni"));
                mesero.setTelefono(rs.getString("telefono"));
                meseros.add(mesero);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener meseros: " + e.getMessage());
        }
        return meseros;
    }
}
