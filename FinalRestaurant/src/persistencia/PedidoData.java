package persistencia;

import entidades.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoData {
    private Connection connection;

    public PedidoData(Connection connection) {
        this.connection = connection;
    }

    public void agregarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedido (id_mesa, id_mesero, fecha, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedido.getIdMesa());
            ps.setInt(2, pedido.getIdMesero());
            ps.setTimestamp(3, Timestamp.valueOf(pedido.getFecha())); // Convertir LocalDateTime a Timestamp
            ps.setBoolean(4, pedido.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pedido.setIdPedido(rs.getInt(1)); // Obtener el id generado
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar el pedido: " + e.getMessage());
        }
    }

    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdMesa(rs.getInt("id_mesa"));
                pedido.setIdMesero(rs.getInt("id_mesero"));
                pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime()); // Convertir Timestamp a LocalDateTime
                pedido.setEstado(rs.getBoolean("estado"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    public void eliminarPedido(int idPedido) {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el pedido: " + e.getMessage());
        }
    }

    // Puedes agregar más métodos para actualizar o buscar pedidos

    // Método para actualizar un pedido
    public void actualizarPedido(Pedido pedido) {
        String sql = "UPDATE pedido SET id_mesa = ?, id_mesero = ?, fecha = ?, estado = ? WHERE id_pedido = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pedido.getIdMesa());
            ps.setInt(2, pedido.getIdMesero());
            ps.setTimestamp(3, Timestamp.valueOf(pedido.getFecha())); // Convertir LocalDateTime a Timestamp
            ps.setBoolean(4, pedido.isEstado());
            ps.setInt(5, pedido.getIdPedido());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    // Método para buscar un pedido por ID
    public Pedido buscarPedido(int idPedido) {
        Pedido pedido = null;
        String sql = "SELECT * FROM pedido WHERE id_pedido = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setIdMesa(rs.getInt("id_mesa"));
                pedido.setIdMesero(rs.getInt("id_mesero"));
                pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime()); // Convertir Timestamp a LocalDateTime
                pedido.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el pedido: " + e.getMessage());
        }
        return pedido;
    }
}
