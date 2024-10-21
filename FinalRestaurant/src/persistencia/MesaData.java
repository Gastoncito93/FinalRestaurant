package persistencia;

import entidades.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MesaData {
    private Connection connection;

    public MesaData(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar una mesa
    public void agregarMesa(Mesa mesa) {
        String sql = "INSERT INTO mesa (numero, capacidad, estado, reserva_id) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, mesa.getNumero());
            statement.setInt(2, mesa.getCapacidad());
            statement.setString(3, mesa.getEstado());
            statement.setObject(4, mesa.getReserva() != null ? mesa.getReserva().getId() : null); // Manejo de reserva

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                mesa.setId(rs.getInt(1)); // Establecer el ID generado en la mesa
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al agregar la mesa: " + e.getMessage());
        }
    }

    // Método para obtener todas las mesas
    public List<Mesa> obtenerMesas() {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesa";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mesa mesa = new Mesa(
                    resultSet.getInt("id"),
                    resultSet.getInt("numero"),
                    resultSet.getInt("capacidad"),
                    resultSet.getString("estado"),
                    null // Se puede manejar la relación de reserva aquí si es necesario
                );
                mesas.add(mesa);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener las mesas: " + e.getMessage());
        }

        return mesas;
    }

    // Método para actualizar una mesa
    public void actualizarMesa(Mesa mesa) {
        String sql = "UPDATE mesa SET numero = ?, capacidad = ?, estado = ?, reserva_id = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mesa.getNumero());
            statement.setInt(2, mesa.getCapacidad());
            statement.setString(3, mesa.getEstado());
            statement.setObject(4, mesa.getReserva() != null ? mesa.getReserva().getId() : null);
            statement.setInt(5, mesa.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la mesa: " + e.getMessage());
        }
    }

    // Método para eliminar una mesa
    public void eliminarMesa(int id) {
        String sql = "DELETE FROM mesa WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la mesa: " + e.getMessage());
        }
    }

    // Método para obtener una mesa por su ID
    public Mesa obtenerMesaPorId(int id) {
        Mesa mesa = null;
        String sql = "SELECT * FROM mesa WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mesa = new Mesa(
                    resultSet.getInt("id"),
                    resultSet.getInt("numero"),
                    resultSet.getInt("capacidad"),
                    resultSet.getString("estado"),
                    null // Se puede manejar la relación de reserva aquí si es necesario
                );
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la mesa por ID: " + e.getMessage());
        }

        return mesa;
    }
}

