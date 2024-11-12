package persistencia;

import entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoData {
    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public ProductoData(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo producto
    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO Producto (nombre, cantidad, precio, tipo, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setInt(2, producto.getCantidad());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setString(4, producto.getTipo());
            pstmt.setBoolean(5, producto.isEstado());

            pstmt.executeUpdate();

            // Obtener el ID generado y asignarlo al objeto producto
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
            }

            System.out.println("Producto insertado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    // Método para obtener un producto por su ID
    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM Producto WHERE id_producto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setTipo(rs.getString("tipo"));
                producto.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto: " + e.getMessage());
        }
        return producto;
    }

    // Método para actualizar un producto
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE Producto SET nombre = ?, cantidad = ?, precio = ?, tipo = ?, estado = ? WHERE id_producto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setInt(2, producto.getCantidad());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setString(4, producto.getTipo());
            pstmt.setBoolean(5, producto.isEstado());
            pstmt.setInt(6, producto.getIdProducto());

            pstmt.executeUpdate();
            System.out.println("Producto actualizado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto por su ID
    public void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM Producto WHERE id_producto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            pstmt.executeUpdate();
            System.out.println("Producto eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setTipo(rs.getString("tipo"));
                producto.setEstado(rs.getBoolean("estado"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return productos;
    }
    public List<Producto> obtenerProductosPorPedido(int pedidoId) {
    List<Producto> productos = new ArrayList<>();
    try {
        String consultaSQL = "SELECT id_producto, nombre, cantidad, precio, tipo, estado FROM producto WHERE id_pedido = ?";
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



}
