package finalrestaurant;

import conexion.Conexion;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Pedido;
import entidades.Producto;
import persistencia.MesaData;
import persistencia.MeseroData;
import persistencia.ProductoData;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import persistencia.PedidoData;
import persistencia.PedidoProductoData;
import persistencia.ReservaData;
import entidades.Reserva;
import java.sql.SQLException;

public class FinalRestaurant {

    public static void main(String[] args) throws SQLException {
        Connection conexion = Conexion.getConexion();

        // ----------------------------------------------------------------------------------      
        MesaData mesaData = new MesaData(conexion);

        // Agregar mesas asegurándote de que los números son únicos
        Mesa mesa1 = new Mesa(101, 4, 2, true, null);
        mesaData.agregarMesa(mesa1);
        System.out.println("Mesa agregada: " + mesa1);

        Mesa mesa2 = new Mesa(102, 5, 4, true, null);
        mesaData.agregarMesa(mesa2);
        System.out.println("Mesa agregada: " + mesa2);

        // Obtener y mostrar todas las mesas
        List<Mesa> mesas = mesaData.obtenerMesas();
        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }
 
        // ----------------------------------------------------------------------------------             
        ProductoData productoData = new ProductoData(conexion);

        // Agregar productos
        Producto producto1 = new Producto(1, "Paco", 4, 150.50, "Perfumería", true);
        productoData.insertarProducto(producto1);
        System.out.println("Producto agregado: " + producto1);

        Producto producto2 = new Producto(2, "Shampoo", 5, 250.75, "Higiene", true);
        productoData.insertarProducto(producto2);
        System.out.println("Producto agregado: " + producto2);

        Producto producto3 = new Producto(3, "Jabón", 3, 100.00, "Higiene", true);
        productoData.insertarProducto(producto3);
        System.out.println("Producto agregado: " + producto3);

        Producto producto4 = new Producto(4, "Perfume", 6, 500.00, "Perfumería", true);
        productoData.insertarProducto(producto4);
        System.out.println("Producto agregado: " + producto4);

        Producto producto5 = new Producto(5, "Desodorante", 2, 80.00, "Higiene", true);
        productoData.insertarProducto(producto5);
        System.out.println("Producto agregado: " + producto5);

        // Obtener y mostrar todos los productos
        List<Producto> productos = productoData.obtenerTodosLosProductos();
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // ----------------------------------------------------------------------------------
        // Instanciar MeseroData para interactuar con la base de datos
        MeseroData meseroData = new MeseroData(conexion);

        // Agregar meseros
        Mesero mesero1 = new Mesero("Carlos", "Pérez", "12345678", true, "carlos123", "password");
        meseroData.agregarMesero(mesero1);
        System.out.println("Mesero agregado: " + mesero1);

        Mesero mesero2 = new Mesero("Ana", "García", "87654321", true, "ana123", "password");
        meseroData.agregarMesero(mesero2);
        System.out.println("Mesero agregado: " + mesero2);

        // Obtener y mostrar todos los meseros
        List<Mesero> meseros = meseroData.obtenerMeseros();
        for (Mesero mesero : meseros) {
            System.out.println(mesero);
        }

        // ----------------------------------------------------------------------------------
        // Agregar pedidos asegurándote de que las mesas y meseros existen
        PedidoData pedidoData = new PedidoData(conexion);

        Pedido pedido1 = new Pedido(mesa1.getIdMesa(), mesero1.getIdMesero(), LocalDateTime.now(), true);
        pedidoData.agregarPedido(pedido1);
        System.out.println("Pedido agregado: " + pedido1);

        Pedido pedido2 = new Pedido(mesa2.getIdMesa(), mesero2.getIdMesero(), LocalDateTime.now(), true);
        pedidoData.agregarPedido(pedido2);
        System.out.println("Pedido agregado: " + pedido2);

        // Obtener y mostrar todos los pedidos
        List<Pedido> pedidos = pedidoData.obtenerPedidos(); // Implementa este método en PedidoData
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }

        // Instanciar PedidoProductoData para interactuar con la base de datos
        PedidoProductoData pedidoProductoData = new PedidoProductoData(conexion);

        pedidoProductoData.agregarProductoAPedido(pedido1.getIdPedido(), producto1.getIdProducto(), 2); // 2 unidades de producto1
        pedidoProductoData.agregarProductoAPedido(pedido1.getIdPedido(), producto2.getIdProducto(), 1); // 1 unidad de producto2
        System.out.println("Productos agregados al pedido: " + pedido1.getIdPedido());

        pedidoProductoData.agregarProductoAPedido(pedido2.getIdPedido(), producto3.getIdProducto(), 3); // 3 unidades de producto3
        System.out.println("Productos agregados al pedido: " + pedido2.getIdPedido());

        // Obtener y mostrar productos del primer pedido
        System.out.println("Productos del pedido " + pedido1.getIdPedido() + ":");
        pedidoProductoData.obtenerProductosPorPedido(pedido1.getIdPedido());

        // Obtener y mostrar productos del segundo pedido
        System.out.println("Productos del pedido " + pedido2.getIdPedido() + ":");
        pedidoProductoData.obtenerProductosPorPedido(pedido2.getIdPedido());
        
        //creando una reserva
        ReservaData reservaData = new ReservaData(conexion);

        Reserva reserva1 = new Reserva(20, "Nico Nico", "33333333", "2024-02-21", "21:05:01", true);
        reservaData.crearReserva(reserva1);

        Reserva reserva2 = new Reserva(20, "Maca Rena", "11111111", "2024-02-19", "19:00:00", true);
        reservaData.crearReserva(reserva2);

        //Mostrar todas las reservas
        List<Reserva> reservas = reservaData.listaDeReservas();

        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
         
        reservaData.eliminarReserva(11);

        List<Reserva> reservas1 = reservaData.listaDeReservas();
        for (Reserva reserva : reservas1) {
            System.out.println(reserva);

        }
    }
}
