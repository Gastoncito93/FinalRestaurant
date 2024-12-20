package persistencia;

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
    public void agregarProductoAPedido(int idPedido, int idProducto, int cantidad) {
    double precio = obtenerPrecioProducto(idProducto); // Método para obtener el precio del producto
    double precioTotal = precio * cantidad;

    String sql = "INSERT INTO pedido_producto (id_pedido, id_producto, cantidad, precio, estado) VALUES (?, ?, ?, ?, 0)";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idPedido);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);
        ps.setDouble(4, precioTotal);

        ps.executeUpdate();
        System.out.println("Producto agregado al pedido con éxito con un precio total de: " + precioTotal);
    } catch (SQLException e) {
        System.out.println("Error al agregar producto al pedido: " + e.getMessage());
    }
}
    private double obtenerPrecioProducto(int idProducto) {
    String sql = "SELECT precio FROM producto WHERE id_producto = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idProducto);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("precio");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el precio del producto: " + e.getMessage());
    }
    return 0.0; // Retorna 0 si no encuentra el producto o si hay un error
}


   
    // Método para obtener productos de un pedido
public List<Producto> obtenerProductosPorPedido(int pedidoId) {
    List<Producto> productos = new ArrayList<>();
    try {
        String consultaSQL = "SELECT pp.id_pedido, pp.id_producto, p.nombre, pp.cantidad, p.precio, (pp.cantidad * p.precio) as subtotal, p.tipo, pp.estado " +
                             "FROM producto p " +
                             "JOIN pedido_producto pp ON p.id_producto = pp.id_producto " +
                             "WHERE pp.id_pedido = ?";
        PreparedStatement ps = connection.prepareStatement(consultaSQL);
        ps.setInt(1, pedidoId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setIdPedido(rs.getInt("id_pedido"));  // Obtener id_pedido de la consulta
            producto.setIdProducto(rs.getInt("id_producto"));
            producto.setNombre(rs.getString("nombre"));
            producto.setCantidad(rs.getInt("cantidad"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setTipo(rs.getString("tipo"));
            producto.setEstado(rs.getBoolean("estado"));

            productos.add(producto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productos;
}

public void eliminarProductosPorPedido(int idPedido) {
    String sql = "DELETE FROM pedido_producto WHERE id_pedido = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idPedido);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error al eliminar los productos del pedido: " + e.getMessage());
    }
}
public void eliminarProductoDePedido(int idPedido, int idProducto) {
    String sql = "DELETE FROM pedido_producto WHERE id_pedido = ? AND id_producto = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idPedido);
        ps.setInt(2, idProducto);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error al eliminar el producto del pedido: " + e.getMessage());
    }
}


    
}
