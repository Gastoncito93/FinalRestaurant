
package Persistencia;

import entidades.Mesa;
import entidades.Mesero;
import entidades.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoData {
    private Connection connection;

    public PedidoData(Connection connection) {
        this.connection = connection;
    }

    public void guardarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedido (mesa_id, mesero_id, estado, fecha, total) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pedido.getMesa().getId());
            ps.setInt(2, pedido.getMesero().getId());
            ps.setString(3, pedido.getEstado());
            ps.setDate(4, new java.sql.Date(pedido.getFecha().getTime())); // Conversión correcta de fecha
            ps.setDouble(5, pedido.getTotal());
            ps.executeUpdate();
            System.out.println("Pedido guardado.");
        } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setMesa(new Mesa(rs.getInt("mesa_id")));  // Solo carga ID
                pedido.setMesero(new Mesero(rs.getInt("mesero_id")));  // Solo carga ID
                pedido.setEstado(rs.getString("estado"));
                pedido.setFecha((Date) new java.util.Date(rs.getDate("fecha").getTime()));  // Conversión de fecha
                pedido.setTotal(rs.getDouble("total"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos: " + e.getMessage());
            e.printStackTrace();
        }
        return pedidos;
    }

    public List<Pedido> obtenerPedidosPorMesero(int meseroId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE mesero_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, meseroId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setMesa(new Mesa(rs.getInt("mesa_id")));  // Solo carga ID
                pedido.setMesero(new Mesero(rs.getInt("mesero_id")));  // Solo carga ID
                pedido.setEstado(rs.getString("estado"));
                pedido.setFecha((Date) new java.util.Date(rs.getDate("fecha").getTime()));  // Conversión de fecha
                pedido.setTotal(rs.getDouble("total"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos por mesero: " + e.getMessage());
            e.printStackTrace();
        }
        return pedidos;
    }

    public List<Pedido> obtenerPedidosPorMesaYFecha(int mesaId, Date fecha) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE mesa_id = ? AND fecha = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, mesaId);
            ps.setDate(2, new java.sql.Date(fecha.getTime()));  // Conversión de fecha
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setMesa(new Mesa(rs.getInt("mesa_id")));  // Solo carga ID
                pedido.setMesero(new Mesero(rs.getInt("mesero_id")));  // Solo carga ID
                pedido.setEstado(rs.getString("estado"));
                pedido.setFecha((Date) new java.util.Date(rs.getDate("fecha").getTime()));  // Conversión de fecha
                pedido.setTotal(rs.getDouble("total"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos por mesa y fecha: " + e.getMessage());
            e.printStackTrace();
        }
        return pedidos;
    }

    public double obtenerIngresosPorFecha(Date fecha) {
        double ingresos = 0;
        String sql = "SELECT SUM(total) as ingresos FROM pedido WHERE fecha = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(fecha.getTime()));  // Conversión de fecha
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ingresos = rs.getDouble("ingresos");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ingresos por fecha: " + e.getMessage());
            e.printStackTrace();
        }
        return ingresos;
    }
}
