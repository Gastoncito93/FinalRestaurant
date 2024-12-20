package vistas;

import entidades.Pedido;
import entidades.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.PedidoData;
import persistencia.PedidoProductoData;
import persistencia.ProductoData;

/**
 *
 * @author Usuario
 */
public class Vista_CargarOrden extends javax.swing.JInternalFrame {

    private Connection connection;
    private PedidoData pedidoData;
    private ProductoData productoData;
    private PedidoProductoData pedidoProductoData;
    private DefaultTableModel modelo;
    private List<Pedido> pedidos;
    private List<Producto> productos;

    public Vista_CargarOrden() {
        initComponents();
        conectarBaseDeDatos();
        pedidoData = new PedidoData(connection);
        productoData = new ProductoData(connection);
        pedidoProductoData = new PedidoProductoData(connection);
        inicializarModelo();
        cargarPedidosEnCombo();
        cargarProductosEnCombo();
        consultarProductosPorPedido();

    }

    private void conectarBaseDeDatos() {
        String url = "jdbc:mariadb://127.0.0.1:3306/restaurant";
        String user = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión a la base de datos exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private void inicializarModelo() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Pedido N°");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Estado");
        modelo.addColumn("Subtotal");
        jTPedidoProducto.setModel(modelo);
    }

    private void cargarPedidosEnCombo() {
        pedidos = pedidoData.obtenerPedidos();
        jCBPedidosDisponibles.removeAllItems();
        for (Pedido pedido : pedidos) {
            jCBPedidosDisponibles.addItem(String.valueOf(pedido.getIdPedido()));

        }
    }

    private void cargarProductosEnCombo() {
        productos = productoData.obtenerTodosLosProductos();
        jCBProductos.removeAllItems();
        for (Producto producto : productos) {
            jCBProductos.addItem(producto.getNombre());

        }
    }

    private void consultarProductosPorPedido() {
        String pedidoSeleccionadoStr = (String) jCBPedidosDisponibles.getSelectedItem();
        if (pedidoSeleccionadoStr != null && !pedidoSeleccionadoStr.isEmpty()) {
            int pedidoSeleccionado = Integer.parseInt(pedidoSeleccionadoStr);
            List<Producto> productosPedido = pedidoProductoData.obtenerProductosPorPedido(pedidoSeleccionado);
            modelo.setRowCount(0); // Limpiar la tabla antes de volver a cargar los datos

            int numeroPedido = 1;
            double totalPrecio = 0.0;  // Variable para calcular el precio total

            for (Producto producto : productosPedido) {
                double subtotal = producto.getPrecio() * producto.getCantidad();
                totalPrecio += subtotal;

                Object[] fila = {
                    producto.getIdPedido(),
                    producto.getNombre(),
                    producto.getCantidad(),
                    producto.isEstado(),
                    subtotal
                };
                modelo.addRow(fila);
                numeroPedido++;
            }

            // Actualizar la tabla
            jTPedidoProducto.setModel(modelo);
            jTPedidoProducto.revalidate();
            jTPedidoProducto.repaint();

            // Actualizar el campo de texto con el precio total
            jTFPrecioTotal.setText(String.valueOf(totalPrecio));
        } else {
            // Manejar el caso en el que no hay un pedido seleccionado o la cadena está vacía
            System.out.println("No se ha seleccionado un pedido válido.");
            jTFPrecioTotal.setText("0.0"); // Restablecer el campo de texto a 0.0
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jCBProductos = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jCBPedidosDisponibles = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSProducto = new javax.swing.JSpinner();
        jBCargarProducto = new javax.swing.JButton();
        jBEntregado = new javax.swing.JButton();
        jBCobrar = new javax.swing.JButton();
        jTFPrecioTotal = new javax.swing.JTextField();
        jBEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPedidoProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jCBProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBProductosActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pedidos Disponibles");

        jCBPedidosDisponibles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBPedidosDisponibles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBPedidosDisponiblesItemStateChanged(evt);
            }
        });
        jCBPedidosDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBPedidosDisponiblesActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu1.png"))); // NOI18N
        jLabel6.setText("Crear Detalle del Pedido");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Productos Disponibles");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stock.png"))); // NOI18N
        jLabel9.setText("Cantidad");

        jSProducto.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        jBCargarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBCargarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carro.png"))); // NOI18N
        jBCargarProducto.setText("Cargar Producto");
        jBCargarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarProductoActionPerformed(evt);
            }
        });

        jBEntregado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBEntregado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/entregado.png"))); // NOI18N
        jBEntregado.setText("Entregado");
        jBEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEntregadoActionPerformed(evt);
            }
        });

        jBCobrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cobrar.png"))); // NOI18N
        jBCobrar.setText("Cobrar/Finalizar");
        jBCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCobrarActionPerformed(evt);
            }
        });

        jTFPrecioTotal.setEditable(false);
        jTFPrecioTotal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFPrecioTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jBEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar1.png"))); // NOI18N
        jBEliminar.setText("Eliminar Producto");
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBCobrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPrecioTotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jCBPedidosDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jCBProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBCargarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jSProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jBEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBPedidosDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCargarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminar))
                .addGap(18, 18, 18)
                .addComponent(jBEntregado)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jTFPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))))
        );

        jTPedidoProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTPedidoProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTPedidoProducto);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rotiseria Grupo 10.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBPedidosDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPedidosDisponiblesActionPerformed
        consultarProductosPorPedido();
    }//GEN-LAST:event_jCBPedidosDisponiblesActionPerformed

    private void jBCargarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarProductoActionPerformed
        int cantidad = (Integer) jSProducto.getValue();
    int pedidoSeleccionado = Integer.parseInt((String) jCBPedidosDisponibles.getSelectedItem());
    String productoSeleccionado = (String) jCBProductos.getSelectedItem();

    if (cantidad == 0) {
        JOptionPane.showMessageDialog(this, "Por favor seleccione una cantidad para agregar.");
        return;
    }

    int idProducto = -1;
    for (Producto producto : productos) {
        if (producto.getNombre().equals(productoSeleccionado)) {
            idProducto = producto.getIdProducto();

            // Verificamos si la cantidad solicitada excede el stock
            if (cantidad > producto.getCantidad()) {
                // Si la cantidad solicitada es mayor que la cantidad en stock, muestra el mensaje
                JOptionPane.showMessageDialog(this, "La cantidad solicitada supera nuestro stock. Solo tenemos " 
                    + producto.getCantidad() + " unidades de " + producto.getNombre() + " disponibles.");
                return;  // Salir del método sin agregar el producto
            }
            break;
        }
    }

    if (idProducto != -1) {
        // Agregar el producto al pedido si la cantidad es válida
        pedidoProductoData.agregarProductoAPedido(pedidoSeleccionado, idProducto, cantidad);
        productoData.actualizarCantidadProducto(idProducto, cantidad);
        consultarProductosPorPedido();
        JOptionPane.showMessageDialog(this, "Producto agregado al pedido exitosamente.");
    } else {
        JOptionPane.showMessageDialog(this, "Error al encontrar el producto.");
    }
    }//GEN-LAST:event_jBCargarProductoActionPerformed

    private void jBEntregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEntregadoActionPerformed
        int filaSeleccionada = jTPedidoProducto.getSelectedRow();

        if (filaSeleccionada != -1) {
            int idPedido = (Integer) jTPedidoProducto.getValueAt(filaSeleccionada, 0);
            String nombreProducto = (String) jTPedidoProducto.getValueAt(filaSeleccionada, 1);
            int idProducto = buscarProductoPorNombre(nombreProducto).getIdProducto();
            int cantidad = (Integer) jTPedidoProducto.getValueAt(filaSeleccionada, 2);

            entregarProductoPedido(idPedido, idProducto, cantidad);
            
            consultarProductosPorPedido();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para entregar.");
        }
    }//GEN-LAST:event_jBEntregadoActionPerformed

    private void jCBProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBProductosActionPerformed

    private void jCBPedidosDisponiblesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBPedidosDisponiblesItemStateChanged

    }//GEN-LAST:event_jCBPedidosDisponiblesItemStateChanged

    private void jBCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCobrarActionPerformed
        if (todosLosProductosEntregados()) {
            String pedidoSeleccionadoStr = (String) jCBPedidosDisponibles.getSelectedItem();
            if (pedidoSeleccionadoStr != null && !pedidoSeleccionadoStr.isEmpty()) {
                int pedidoSeleccionado = Integer.parseInt(pedidoSeleccionadoStr);

                pedidoProductoData.eliminarProductosPorPedido(pedidoSeleccionado);

                pedidoData.eliminarPedido(pedidoSeleccionado);

                String total = jTFPrecioTotal.getText();
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "El cliente ha realizado el pago de " + total + "$. Pedido eliminado.");

                // Actualizar el JComboBox y la tabla
                cargarPedidosEnCombo();
                consultarProductosPorPedido(); // Esto limpiará la tabla ya que no habrá un pedido seleccionado
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un pedido para cobrar.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se puede cobrar hasta que todos los productos sean entregados.");
        }

    }//GEN-LAST:event_jBCobrarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        int filaSeleccionada = jTPedidoProducto.getSelectedRow();
        if (filaSeleccionada != -1) {
            //tomar los datos de la fila seleccionada
            String pedidoSeleccionadoStr = (String) jCBPedidosDisponibles.getSelectedItem();
            int idPedido = Integer.parseInt(pedidoSeleccionadoStr);
            String nombreProducto = (String) modelo.getValueAt(filaSeleccionada, 1);

            //buscar el id del producto por su nombre
            Producto producto = buscarProductoPorNombre(nombreProducto);
            int idProducto = producto.getIdProducto();

            //metodo para eliminar el producto
            pedidoProductoData.eliminarProductoDePedido(idPedido, idProducto);

            //mostramos el mensaje de exito y actualizamos
            JOptionPane.showMessageDialog(this, "Producto eliminado del pedido.");
            consultarProductosPorPedido();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto para eliminar.");
        }
    }//GEN-LAST:event_jBEliminarActionPerformed

    public void entregarProductoPedido(int idPedido, int idProducto, int cantidad) {
        String sql = "UPDATE pedido_producto SET estado = 1 WHERE id_pedido = ? AND id_producto = ? AND cantidad = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Producto entregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el producto con los datos especificados.");
            }
        } catch (SQLException e) {
            System.err.println("Error al entregar el producto del pedido: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar entregar el producto.");
        }
    }

    public Producto buscarProductoPorNombre(String nombreProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE nombre like ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Recuperar los datos del producto
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");
                boolean estado = rs.getBoolean("estado");

                // Crear el objeto Producto
                producto = new Producto(idProducto, nombre, cantidad, precio, tipo, estado);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el producto: " + e.getMessage());
        }
        return producto;
    }

    private boolean todosLosProductosEntregados() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            boolean estado = (boolean) modelo.getValueAt(i, 3); // Asegúrate de que 3 es el índice de la columna "Estado"
            if (!estado) {
                return false;
            }
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCargarProducto;
    private javax.swing.JButton jBCobrar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBEntregado;
    private javax.swing.JComboBox<String> jCBPedidosDisponibles;
    private javax.swing.JComboBox<String> jCBProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFPrecioTotal;
    private javax.swing.JTable jTPedidoProducto;
    // End of variables declaration//GEN-END:variables
}
