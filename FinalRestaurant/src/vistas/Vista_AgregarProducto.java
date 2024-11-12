/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import entidades.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ProductoData;

/**
 *
 * @author Lenovo
 */
public class Vista_AgregarProducto extends javax.swing.JInternalFrame {
    
    private ButtonGroup grupoEstado;
    private Connection connection;
    ProductoData productoData = new ProductoData(connection);
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    
    public Vista_AgregarProducto() {
        initComponents();
      conectarBaseDeDatos(); // Primero, establece la conexión
        productoData = new ProductoData(connection); // Luego, inicializa MesaData
        inicializarModelo();
        grupoEstado = new ButtonGroup();
        grupoEstado.add(jRBAlta);
        grupoEstado.add(jRBBaja);
        consultar();
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
            return; // Detener la ejecución si no se puede conectar
        }
    }
    
    private void inicializarModelo() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Tipo");
        modelo.addColumn("Estado");
        jTProducto.setModel(modelo);
    }
    
    
    
    void consultar() {
        String sql = "SELECT id_producto, nombre, cantidad, precio, tipo, estado FROM producto";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Object[] producto = new Object[6];
            modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                producto[0] = rs.getInt("id_producto");
                producto[1] = rs.getString("nombre");
                producto[2] = rs.getInt("cantidad");
                producto[3] = rs.getDouble("precio");
                producto[4] = rs.getString("tipo");
                producto[5] = rs.getBoolean("estado");
                modelo.addRow(producto);
            }

            jTProducto.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarDatosProductoSeleccionada(int rowIndex) {
    // Obtén los datos de la mesa seleccionada
    int id_producto = (int) modelo.getValueAt(rowIndex, 0); 
    String nombre = (String) modelo.getValueAt(rowIndex, 1); 
    int cantidad = (int) modelo.getValueAt(rowIndex, 2); 
    double precio = (double) modelo.getValueAt(rowIndex, 3);
    String tipo = (String) modelo.getValueAt(rowIndex, 4);
    boolean estado = (boolean) modelo.getValueAt(rowIndex, 5); 

    // Establece los valores en los campos de texto
    jTFNombre.setText(String.valueOf(nombre));
    jTFCantidad.setText(String.valueOf(cantidad));
    jTFPrecio.setText(String.valueOf(precio));
    jTFTipo.setText(String.valueOf(tipo));

    // Establece el estado en los radio buttons
    if (estado) {
        jRBAlta.setSelected(true);
        jRBBaja.setSelected(false);
    } else {
        jRBAlta.setSelected(false);
        jRBBaja.setSelected(true);
    }
    }
    
    private void setearTodoEnVacio(){
        // Limpiar los campos de texto
        jTFNombre.setText("");
        jTFCantidad.setText("");
        jTFPrecio.setText("");
        jTFTipo.setText("");
        jRBAlta.setSelected(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFCantidad = new javax.swing.JTextField();
        jTFPrecio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jBAgregar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTFTipo = new javax.swing.JTextField();
        jRBAlta = new javax.swing.JRadioButton();
        jRBBaja = new javax.swing.JRadioButton();

        jTextField3.setText("jTextField3");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jTProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        jTProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTProducto);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tabla de productos en Stock");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Agregar Producto");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Precio");

        jTFPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFPrecioActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setText("Cantidad");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Nombre del producto");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel7.setText("Estado");

        jBAgregar.setText("Agregar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setText("Tipo");

        jTFTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTipoActionPerformed(evt);
            }
        });

        jRBAlta.setText("Alta");
        jRBAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBAltaActionPerformed(evt);
            }
        });

        jRBBaja.setText("Baja");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTFCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTFPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTFTipo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(jRBAlta)))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jRBBaja)
                                        .addComponent(jBActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jTFNombre))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 27, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRBAlta)
                            .addComponent(jRBBaja))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBAgregar)
                            .addComponent(jBActualizar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFPrecioActionPerformed

    private void jTFTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTipoActionPerformed

    private void jRBAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBAltaActionPerformed

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
       // Método para agregar un nuevo product
    try {
        
        String nombre = jTFNombre.getText();
        int cantidad = Integer.parseInt(jTFCantidad.getText());
        double precio = Double.parseDouble(jTFPrecio.getText());
        String tipo = jTFTipo.getText();
        boolean estado = jRBAlta.isSelected(); // suponiendo que jRActivo es el botón de selección para el estado

        Producto nuevoProducto = new Producto(nombre, cantidad, precio, tipo, estado);
        productoData.insertarProducto(nuevoProducto); // Agrega el producto a la base de datos
        actualizarTabla(); // Actualiza la tabla después de agregar
        
        setearTodoEnVacio();
                
        JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para cantidad y precio.");
    }
}
   

    // Método auxiliar para actualizar la tabla de productos
    private void actualizarTabla() {
        modelo.setRowCount(0); // Limpiar el modelo de la tabla
        List<Producto> productos = productoData.obtenerTodosLosProductos(); // Obtener todos los productos

        for (Producto producto : productos) {
            Object[] fila = {producto.getIdProducto(), producto.getNombre(), producto.getCantidad(), producto.getPrecio(), producto.getTipo(), producto.isEstado()};
            modelo.addRow(fila); // Agregar cada producto como una nueva fila en la tabla
        }


    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jTProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTProductoMouseClicked
        int rowIndex = jTProducto.getSelectedRow();
        if (rowIndex != -1) { // Asegúrate de que haya una fila seleccionada
            cargarDatosProductoSeleccionada(rowIndex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTProductoMouseClicked

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int filaSeleccionada = jTProducto.getSelectedRow();
    if (filaSeleccionada != -1) {
        int idProducto = (int) modelo.getValueAt(filaSeleccionada, 0);
        String nombre = jTFNombre.getText();
        int cantidad = Integer.parseInt(jTFCantidad.getText());
        double precio = Double.parseDouble(jTFPrecio.getText());
        String tipo = jTFTipo.getText();
        boolean estado = jRBAlta.isSelected();

        Producto nuevoProducto = new Producto(idProducto, nombre, cantidad, precio, tipo, estado);

        productoData.actualizarProducto(nuevoProducto);
        
        consultar(); 
        
        setearTodoEnVacio();

        JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un Producto para actualizar.");
    }
    }//GEN-LAST:event_jBActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRBAlta;
    private javax.swing.JRadioButton jRBBaja;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCantidad;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFPrecio;
    private javax.swing.JTextField jTFTipo;
    private javax.swing.JTable jTProducto;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
