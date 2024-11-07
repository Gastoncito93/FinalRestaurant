package persistencia;

import entidades.PedidoProducto;
import entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoProductoData {
    private Connection connection;

    public PedidoProductoData(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar productos a un pedido
    public void agregarProductoAPedido(int id_pedido, int id_producto, int cantidad) {
        String sql = "INSERT INTO pedido_producto (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_pedido);
            ps.setInt(2, id_producto);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            System.out.println("Producto agregado al pedido con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al agregar producto al pedido: " + e.getMessage());
        }
    }

   
    // Método para obtener productos de un pedido
public List<Producto> obtenerProductosPorPedido(int id_pedido) {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT pp.id_producto, p.nombre, pp.cantidad " +
                 "FROM pedido_producto pp JOIN Producto p ON pp.id_producto = p.id_producto " + // Asegúrate que 'id_producto' es el nombre correcto
                 "WHERE pp.id_pedido = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, id_pedido);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idProducto = rs.getInt("id_producto");
            String nombre = rs.getString("nombre");
            int cantidad = rs.getInt("cantidad");
            productos.add(new Producto(idProducto, nombre, cantidad)); // Asume que tienes un constructor adecuado
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener productos del pedido: " + e.getMessage());
    }
    return productos;
}
    


    // Otros métodos según sea necesario, como eliminar o actualizar productos de un pedido.
}
