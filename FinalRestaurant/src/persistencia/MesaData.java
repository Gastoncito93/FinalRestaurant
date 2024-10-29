package persistencia;

import entidades.Mesa;
import entidades.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesaData {
    private Connection connection;

    // Constructor
    public MesaData(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar una nueva mesa
    public void agregarMesa(Mesa mesa) {
        String sql = "INSERT INTO Mesa (numero, capacidad, estado, id_reserva) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, mesa.getNumero());
            statement.setInt(2, mesa.getCapacidad());
            statement.setBoolean(3, mesa.isEstado());
            // Asegúrate de manejar el caso donde reserva puede ser null
            if (mesa.getReserva() != null) {
                statement.setInt(4, mesa.getReserva().getIdReserva()); // Asumiendo que hay un método getIdReserva en la clase Reserva
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            statement.executeUpdate();

            // Obtener el id_mesa generado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                mesa.setIdMesa(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una mesa por su id
    public Mesa obtenerMesa(int idMesa) {
        Mesa mesa = null;
        String sql = "SELECT * FROM Mesa WHERE id_mesa = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idMesa);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mesa = new Mesa();
                mesa.setIdMesa(resultSet.getInt("id_mesa"));
                mesa.setNumero(resultSet.getInt("numero"));
                mesa.setCapacidad(resultSet.getInt("capacidad"));
                mesa.setEstado(resultSet.getBoolean("estado"));

                // Obtener la reserva si está relacionada
                int idReserva = resultSet.getInt("id_reserva");
                if (!resultSet.wasNull()) {
                    Reserva reserva = new Reserva(); // Aquí debes implementar la lógica para obtener la reserva
                    reserva.setIdReserva(idReserva); // Suponiendo que la clase Reserva tiene este método
                    mesa.setReserva(reserva);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesa;
    }

    // Método para actualizar una mesa
    public void actualizarMesa(Mesa mesa) {
        String sql = "UPDATE Mesa SET numero = ?, capacidad = ?, estado = ?, id_reserva = ? WHERE id_mesa = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mesa.getNumero());
            statement.setInt(2, mesa.getCapacidad());
            statement.setBoolean(3, mesa.isEstado());

            if (mesa.getReserva() != null) {
                statement.setInt(4, mesa.getReserva().getIdReserva());
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            statement.setInt(5, mesa.getIdMesa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una mesa
    public void eliminarMesa(int idMesa) {
        String sql = "DELETE FROM Mesa WHERE id_mesa = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idMesa);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todas las mesas
    public List<Mesa> obtenerMesas() {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM Mesa";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setIdMesa(resultSet.getInt("id_mesa"));
                mesa.setNumero(resultSet.getInt("numero"));
                mesa.setCapacidad(resultSet.getInt("capacidad"));
                mesa.setEstado(resultSet.getBoolean("estado"));

                // Obtener la reserva si está relacionada
                int idReserva = resultSet.getInt("id_reserva");
                if (!resultSet.wasNull()) {
                    Reserva reserva = new Reserva(); // Aquí debes implementar la lógica para obtener la reserva
                    reserva.setIdReserva(idReserva); // Suponiendo que la clase Reserva tiene este método
                    mesa.setReserva(reserva);
                }

                mesas.add(mesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesas;
    }
}
