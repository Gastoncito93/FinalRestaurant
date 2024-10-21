package persistencia;

import entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoData {

    private Connection connection;

    public ProductoData(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar un producto
    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, cantidadDisponible, precio, categoria) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getCantidadDisponible());
            statement.setDouble(3, producto.getPrecio());
            statement.setString(4, producto.getCategoria());

            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                producto.setCodigo(generatedKeys.getInt(1));
            }

            statement.close();
            System.out.println("Producto agregado exitosamente: " + producto);
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto: " + e.getMessage());
        }
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultSet.getInt("codigo"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCantidadDisponible(resultSet.getInt("cantidadDisponible"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setCategoria(resultSet.getString("categoria"));

                productos.add(producto);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }

        return productos;
    }

    // Método para actualizar un producto
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, cantidadDisponible = ?, precio = ?, categoria = ? WHERE codigo = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, producto.getNombre());
            statement.setInt(2, producto.getCantidadDisponible());
            statement.setDouble(3, producto.getPrecio());
            statement.setString(4, producto.getCategoria());
            statement.setInt(5, producto.getCodigo());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Producto actualizado exitosamente: " + producto);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto
    public void eliminarProducto(int codigo) {
        String sql = "DELETE FROM producto WHERE codigo = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, codigo);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Producto eliminado exitosamente con código: " + codigo);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // Método para obtener un producto por código
    public Producto obtenerProductoPorCodigo(int codigo) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE codigo = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt("codigo"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCantidadDisponible(resultSet.getInt("cantidadDisponible"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setCategoria(resultSet.getString("categoria"));
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto: " + e.getMessage());
        }

        return producto;
    }
}
