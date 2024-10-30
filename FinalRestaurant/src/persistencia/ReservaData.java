package persistencia;

import entidades.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaData {

    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public ReservaData(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo reserva
    public void crearReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva ( id_mesa, nombre_cliente, dni_cliente, fecha, hora, estado ) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, reserva.getId_mesa());
            pstmt.setString(2, reserva.getNombrePersona());
            pstmt.setString(3, reserva.getDni());
            pstmt.setString(4, reserva.getFecha());
            pstmt.setString(5, reserva.getHora());
            pstmt.setBoolean(6, reserva.isEstado());

            pstmt.executeUpdate();

            // Obtener el ID generado y asignarlo al objeto reserva
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                reserva.setIdReserva(rs.getInt(1));
            }

            System.out.println("Reserva creada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al crear la reserva " + e.getMessage());
        }
    }

    // Método para obtener un reserva por su ID
    public Reserva obtenerReservaPorId(int idReserva) {
        Reserva reserva = null;
        String sql = "SELECT * FROM Reserva WHERE id_reserva = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idReserva);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("id_reserva"));
                reserva.setId_mesa(rs.getInt("id_mesa"));
                reserva.setNombrePersona(rs.getString("nombre_cliente"));
                reserva.setDni(rs.getString("dni_cliente"));
                reserva.setFecha(rs.getString("fecha"));
                reserva.setHora(rs.getString("hora"));
                reserva.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la reserva: " + e.getMessage());
        }
        return reserva;
    }

    // Método para eliminar una reserva por su ID
    public void eliminarReserva(int idReserva) {
        String sql = "DELETE FROM Reserva WHERE id_reserva = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idReserva);
            pstmt.executeUpdate();
            System.out.println("Reserva eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }

// Método para listar todos las reservas
    public List<Reserva> listaDeReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        rs.getInt("id_mesa"),
                        rs.getString("nombre_cliente"),
                        rs.getString("dni_cliente"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getBoolean("estado")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

}