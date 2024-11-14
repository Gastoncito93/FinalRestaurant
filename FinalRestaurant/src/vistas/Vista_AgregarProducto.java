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
        // Obtén los datos del Producto seleccionado
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
    
    // Setea todos los campos en Vacio
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

        jTProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tabla de productos en Stock");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/producto1.png"))); // NOI18N
        jLabel2.setText("Agregar Producto");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jTFNombre.setNextFocusableComponent(jTFCantidad);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Precio");

        jTFCantidad.setNextFocusableComponent(jTFPrecio);
        jTFCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCantidadActionPerformed(evt);
            }
        });

        jTFPrecio.setNextFocusableComponent(jTFTipo);
        jTFPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFPrecioActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Stock");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nombre del producto");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/estado.png"))); // NOI18N
        jLabel7.setText("Estado");

        jBAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregaboton2.png"))); // NOI18N
        jBAgregar.setText("Agregar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jBActualizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar1.png"))); // NOI18N
        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tipo");

        jTFTipo.setNextFocusableComponent(jTFNombre);
        jTFTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTipoActionPerformed(evt);
            }
        });

        jRBAlta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBAlta.setText("Alta");
        jRBAlta.setNextFocusableComponent(jRBBaja);
        jRBAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBAltaActionPerformed(evt);
            }
        });

        jRBBaja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBBaja.setText("Baja");
        jRBBaja.setNextFocusableComponent(jTFNombre);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFCantidad)
                                    .addComponent(jTFPrecio)
                                    .addComponent(jTFTipo)
                                    .addComponent(jTFNombre)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jBActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jRBAlta)
                                .addGap(46, 46, 46)
                                .addComponent(jRBBaja)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
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
        try {
    // Verifica que los campos de texto no estén vacíos antes de parsearlos
    if (jTFNombre.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre.");
        return;
    }
    if (jTFCantidad.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa una cantidad.");
        return;
    }
    if (jTFPrecio.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un precio.");
        return;
    }
    if (jTFTipo.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un tipo.");
        return;
    }

    // Parseo de campos
    String nombre = jTFNombre.getText();
    int cantidad;
    double precio;
    String tipo = jTFTipo.getText();
    boolean estado = jRBAlta.isSelected();

    // Intenta convertir a int para cantidad
    try {
        cantidad = Integer.parseInt(jTFCantidad.getText());
        if (cantidad < 0) {
            JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
        return;
    }

    // Intenta convertir a double para precio
    try {
        precio = Double.parseDouble(jTFPrecio.getText());
        if (precio < 0) {
            JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
        return;
    }

    // Verifica si ya existe un producto con el mismo nombre y tipo
    if (productoData.existeProductoConNombreYTipo(nombre, tipo)) {
        JOptionPane.showMessageDialog(this, "Ya existe un producto con el mismo nombre y tipo.");
        return;
    }

    // Si no hay duplicados, crea el producto y lo inserta en la base de datos
    Producto nuevoProducto = new Producto(nombre, cantidad, precio, tipo, estado);
    productoData.insertarProducto(nuevoProducto);
    actualizarTabla();
    setearTodoEnVacio();

    JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado. Por favor, inténtalo nuevamente.");
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
    try {
        // Verifica que los campos de texto no estén vacíos antes de parsearlos
        if (jTFNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre.");
            return;
        }
        if (jTFCantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa una cantidad.");
            return;
        }
        if (jTFPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un precio.");
            return;
        }
        if (jTFTipo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un tipo.");
            return;
        }

        // Parseo de campos
        String nombre = jTFNombre.getText();
        int cantidad;
        double precio;
        String tipo = jTFTipo.getText();
        boolean estado = jRBAlta.isSelected();

        // Intenta convertir a int para cantidad
        try {
            cantidad = Integer.parseInt(jTFCantidad.getText());
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
            return;
        }

        // Intenta convertir a double para precio
        try {
            precio = Double.parseDouble(jTFPrecio.getText());
            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
            return;
        }

        // Obtén el ID del producto de la fila seleccionada
        int idProducto = (int) modelo.getValueAt(filaSeleccionada, 0);

        // Crea el objeto Producto actualizado
        Producto nuevoProducto = new Producto(idProducto, nombre, cantidad, precio, tipo, estado);

        // Actualiza el producto en la base de datos
        productoData.actualizarProducto(nuevoProducto);

        // Actualiza la tabla y limpia los campos
        consultar();
        setearTodoEnVacio();

        JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado. Por favor, inténtalo nuevamente.");
    }
} else {
    JOptionPane.showMessageDialog(this, "Por favor, selecciona un Producto para actualizar.");
}

    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jTFCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCantidadActionPerformed


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
