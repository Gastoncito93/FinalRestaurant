/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import entidades.Pedido;
import entidades.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
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
        modelo.setRowCount(0);

        int numeroPedido = 1;
        double totalPrecio = 0.0;  // Variable para calcular el precio total
        for (Producto producto : productosPedido) {
            double subtotal = producto.getPrecio() * producto.getCantidad();
            totalPrecio += subtotal; // Sumar el subtotal al precio total
            Object[] fila = {
                numeroPedido,
                producto.getNombre(),
                producto.getCantidad(),
                "No entregado",
                subtotal
            };
            modelo.addRow(fila);
            numeroPedido++;
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPedidoProducto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jCBProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBProductosActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Crear Detalle del Pedido");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Productos Disponibles");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cantidad");

        jSProducto.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        jBCargarProducto.setText("Cargar Producto");
        jBCargarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarProductoActionPerformed(evt);
            }
        });

        jBEntregado.setText("Entregado");
        jBEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEntregadoActionPerformed(evt);
            }
        });

        jBCobrar.setText("Cobrar/Finalizar");
        jBCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCobrarActionPerformed(evt);
            }
        });

        jTFPrecioTotal.setEditable(false);
        jTFPrecioTotal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTFPrecioTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCBPedidosDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 75, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jBCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jTFPrecioTotal)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBCargarProducto))
                        .addGap(91, 91, 91))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jSProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
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
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBCargarProducto)
                .addGap(38, 38, 38)
                .addComponent(jBEntregado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCobrar)
                    .addComponent(jTFPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBPedidosDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPedidosDisponiblesActionPerformed
        consultarProductosPorPedido(); 
    }//GEN-LAST:event_jCBPedidosDisponiblesActionPerformed

    private void jBCargarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarProductoActionPerformed
         int pedidoSeleccionado = Integer.parseInt((String) jCBPedidosDisponibles.getSelectedItem());
         String productoSeleccionado = (String) jCBProductos.getSelectedItem();
         int cantidad = (Integer) jSProducto.getValue();
         int idProducto = -1;
         for (Producto producto : productos){
          if (producto.getNombre().equals(productoSeleccionado))
         { idProducto = producto.getIdProducto();
         break;
            } 
         } 
         if (idProducto != -1) {
             pedidoProductoData.agregarProductoAPedido(pedidoSeleccionado, idProducto, cantidad);
             consultarProductosPorPedido();
             JOptionPane.showMessageDialog(this, "Producto agregado al pedido exitosamente.");
         } else { 
             JOptionPane.showMessageDialog(this, "Error al encontrar el producto.");
         }
    }//GEN-LAST:event_jBCargarProductoActionPerformed

    private void jBEntregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEntregadoActionPerformed
      int filaSeleccionada = jTPedidoProducto.getSelectedRow();
    if (filaSeleccionada != -1) {
       jTPedidoProducto.setValueAt("Entregado", filaSeleccionada, 3);
        JOptionPane.showMessageDialog(this, "El pedido fue entregado");
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para entregar");
    }  
        
        
    }//GEN-LAST:event_jBEntregadoActionPerformed

    private void jCBProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBProductosActionPerformed

    private void jCBPedidosDisponiblesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBPedidosDisponiblesItemStateChanged
       
    }//GEN-LAST:event_jCBPedidosDisponiblesItemStateChanged

    private void jBCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCobrarActionPerformed
        JOptionPane.showMessageDialog(this, "El cliente ha realizado el pago");
    }//GEN-LAST:event_jBCobrarActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCargarProducto;
    private javax.swing.JButton jBCobrar;
    private javax.swing.JButton jBEntregado;
    private javax.swing.JComboBox<String> jCBPedidosDisponibles;
    private javax.swing.JComboBox<String> jCBProductos;
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
