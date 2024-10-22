package finalrestaurant;

import conexion.Conexion;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Producto;
import java.sql.Connection;
import java.util.List;
import persistencia.MesaData;

import persistencia.ProductoData;

public class FinalRestaurant {

    public static void main(String[] args) {
        
       /* Connection conexion = Conexion.getConexion();
        MesaData mesaData = new MesaData(conexion);

        // Agregar una mesa
        Mesa nuevaMesa = new Mesa(0, 5, 4, "libre", null);
        mesaData.agregarMesa(nuevaMesa);
        System.out.println("Mesa agregada: " + nuevaMesa);

        // Obtener todas las mesas
        List<Mesa> mesas = mesaData.obtenerMesas();
        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }
        
        // Actualizar una mesa
        nuevaMesa.setEstado("ocupada");
        mesaData.actualizarMesa(nuevaMesa);
        
        // Eliminar una mesa
        mesaData.eliminarMesa(nuevaMesa.getId());*/
       
       /*Connection conexion = Conexion.getConexion();
        ProductoData productoData = new ProductoData(conexion);

        // Agregar una mesa
        Producto nuevoProducto = new Producto(0, "Paco", 4, 150.50, "Perfumeria");
        productoData.agregarProducto(nuevoProducto);
        System.out.println("Producto agregado: " + nuevoProducto);

        // Obtener todas las mesas
        List<Producto> productos = productoData.obtenerProductos();
        for (Producto producto : productos) {
            System.out.println(producto);
       }
        
       Connection conexion = Conexion.getConexion();   

        // Instanciar MeseroData para interactuar con la base de datos
        MeseroData meseroData = new MeseroData(conexion);

        // Crear un nuevo mesero para agregar a la base de datos
        Mesero nuevoMesero = new Mesero("Carlos", "Perez", "12345678", "carlos123", "password");

        // Llamar al método agregarMesero para guardar el mesero en la base de datos
        meseroData.agregarMesero(nuevoMesero);

        // Verificar que el mesero fue agregado imprimiendo el objeto
        System.out.println("Mesero agregado: " + nuevoMesero);

        // Buscar mesero por ID y mostrar los datos
        Mesero meseroBuscado = meseroData.buscarMesero(nuevoMesero.getId());
        if (meseroBuscado != null) {
            System.out.println("Mesero encontrado: " + meseroBuscado);
        } else {
            System.out.println("No se encontró el mesero.");
        }

        // Actualizar los datos del mesero
        nuevoMesero.setNombre("Carlos Alberto");
        meseroData.actualizarMesero(nuevoMesero);
        System.out.println("Mesero actualizado: " + meseroData.buscarMesero(nuevoMesero.getId()));

        // Eliminar el mesero
        meseroData.eliminarMesero(nuevoMesero.getId());
        System.out.println("Mesero eliminado.");*/
    }
    }
