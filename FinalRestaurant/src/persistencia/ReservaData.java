package Persistencia;

import entidades.Mesa;
import entidades.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaData {
    private Connection connection;

    public ReservaData(Connection connection) {
        this.connection = connection;
    }

    public void guardarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (mesa_id, nombre_cliente, dni_cliente, fecha_reserva, hora_reserva, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, reserva.getMesa().getId());
            ps.setString(2, reserva.getNombreCliente());
            ps.setString(3, reserva.getDniCliente());
            ps.setDate(4, new java.sql.Date(reserva.getFechaReserva().getTime())); // Conversión de fecha
            ps.setTime(5, reserva.getHoraReserva()); // Uso correcto de Time
            ps.setString(6, reserva.getEstado());
            ps.executeUpdate();
            System.out.println("Reserva guardada.");
        } catch (SQLException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
            e.printStackTrace();  // Agregando más detalles del error
        }
    }

    public List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT r.*, m.numero, m.capacidad, m.estado as mesa_estado FROM reserva r JOIN mesa m ON r.mesa_id = m.id"; // Cambiado alias para evitar confusión con estado
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();  // Usamos el constructor por defecto

                // Asignando valores a la reserva
                reserva.setId(rs.getInt("id"));
                Mesa mesa = new Mesa();  // Usamos un nuevo objeto Mesa
                mesa.setId(rs.getInt("mesa_id"));
                mesa.setNumero(rs.getInt("numero"));
                mesa.setCapacidad(rs.getInt("capacidad"));
                mesa.setEstado(rs.getString("mesa_estado"));  // Usamos alias para evitar confusión con estado de la reserva

                reserva.setMesa(mesa);
                reserva.setNombreCliente(rs.getString("nombre_cliente"));
                reserva.setDniCliente(rs.getString("dni_cliente"));
                reserva.setFechaReserva(new java.util.Date(rs.getDate("fecha_reserva").getTime()));  // Conversión de fecha
                reserva.setHoraReserva(rs.getTime("hora_reserva"));  // Hora correctamente asignada
                reserva.setEstado(rs.getString("estado"));
                
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reservas: " + e.getMessage());
            e.printStackTrace();  // Agregando más detalles del error
        }
        return reservas;
    }
}


