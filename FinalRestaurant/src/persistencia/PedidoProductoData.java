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
public List<Producto> obtenerProductosPorPedido(int pedidoId) {
    List<Producto> productos = new ArrayList<>();
    try {
        String consultaSQL = "SELECT p.id_producto, p.nombre, p.cantidad, p.precio, p.tipo, p.estado " +
                             "FROM producto p " +
                             "JOIN pedido_producto pp ON p.id_producto = pp.id_producto " +
                             "WHERE pp.id_pedido = ?";
        PreparedStatement ps = connection.prepareStatement(consultaSQL);
        ps.setInt(1, pedidoId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            int idProducto = rs.getInt("id_producto");
            String nombre = rs.getString("nombre");
            int cantidad = rs.getInt("cantidad");
            double precio = rs.getDouble("precio");
            String tipo = rs.getString("tipo");
            boolean estado = rs.getBoolean("estado");

            // Debugging: Imprimir valores obtenidos directamente desde el ResultSet
            System.out.println("DB values - Nombre: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad);

            Producto producto = new Producto(idProducto, nombre, cantidad, precio, tipo, estado);
            productos.add(producto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productos;
}


    


    // Otros métodos según sea necesario, como eliminar o actualizar productos de un pedido.
}
