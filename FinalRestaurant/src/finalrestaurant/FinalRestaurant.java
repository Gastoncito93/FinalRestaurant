package finalrestaurant;

import conexion.Conexion;
import entidades.Mesa;
import java.sql.Connection;
import java.util.List;
import persistencia.MesaData;

public class FinalRestaurant {

    public static void main(String[] args) {
        
        Connection conexion = Conexion.getConexion();
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
        mesaData.eliminarMesa(nuevaMesa.getId());
    }
}
