
package vistas;

import entidades.Mesero;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.MeseroData;

public class Vista_AgregarMesero extends javax.swing.JInternalFrame {
    private ButtonGroup grupoEstado;
    private Connection connection;
    MeseroData meseroData = new MeseroData(connection);
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;

    public Vista_AgregarMesero() {
        initComponents();
      conectarBaseDeDatos(); // Primero, establece la conexión
        meseroData = new MeseroData(connection); // Luego, inicializa MesaData
        inicializarModelo();
        grupoEstado = new ButtonGroup();
        grupoEstado.add(jRActivo);
        grupoEstado.add(jRInactivo);
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
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Estado");
        jTableMesero.setModel(modelo);
    }
    
    void consultar() {
        String sql = "SELECT id_mesero, nombre, apellido, dni, usuario, contraseña, estado FROM mesero";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Object[] mesero = new Object[7];
            modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                mesero[0] = rs.getInt("id_mesero");
                mesero[1] = rs.getString("nombre");
                mesero[2] = rs.getString("apellido");
                mesero[3] = rs.getString("DNI");
                mesero[4] = rs.getString("usuario");
                mesero[5] = rs.getString("contraseña");
                mesero[6] = rs.getBoolean("estado");
                modelo.addRow(mesero);
            }

            jTableMesero.setModel(modelo);

            // Selecciona la primera fila si la tabla tiene datos
            if (modelo.getRowCount() > 0) {
                jTableMesero.setRowSelectionInterval(0, 0);
                cargarDatosMeseroSeleccionado(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarDatosMeseroSeleccionado(int rowIndex) {
    // Obtén los datos de la mesa seleccionada
    int id_mesero = (int) modelo.getValueAt(rowIndex, 0); 
    String nombre = (String) modelo.getValueAt(rowIndex, 1); 
    String apellido = (String) modelo.getValueAt(rowIndex, 2); 
    String dni = (String) modelo.getValueAt(rowIndex, 3); 
    String usuario = (String) modelo.getValueAt(rowIndex, 4); 
    String contraseña = (String) modelo.getValueAt(rowIndex, 5); 
    boolean estado = (boolean) modelo.getValueAt(rowIndex, 6); 

    // Establece los valores en los campos de texto
    jTNombre.setText(String.valueOf(nombre));
    jTApellido.setText(String.valueOf(apellido));
    jTDNI.setText(String.valueOf(dni));
    jTUsuario.setText(String.valueOf(usuario));
    jTContraseña.setText(String.valueOf(contraseña));
    // Establece el estado en los radio buttons
    if (estado) {
        jRActivo.setSelected(true);
        jRInactivo.setSelected(false);
    } else {
        jRActivo.setSelected(false);
        jRInactivo.setSelected(true);
    }
    }

    
    // Método auxiliar para actualizar la tabla de productos
    private void actualizarTabla() {
        modelo.setRowCount(0); // Limpiar el modelo de la tabla
        List<Mesero> meseros = meseroData.obtenerMeseros(); // Obtener todos los productos

        for (Mesero mesero : meseros) {
            Object[] fila = {mesero.getNombre(), mesero.getApellido(), mesero.getDni(), mesero.getUsuario(), mesero.getContraseña(), mesero.isEstado()};
            modelo.addRow(fila); // Agregar cada producto como una nueva fila en la tabla
        }


    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jBAgregar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMesero = new javax.swing.JTable();
        jTNombre = new javax.swing.JTextField();
        jTApellido = new javax.swing.JTextField();
        jTDNI = new javax.swing.JTextField();
        jTUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTContraseña = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRActivo = new javax.swing.JRadioButton();
        jRInactivo = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingresar Mesero");

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

        jTableMesero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "DNI", "Estado"
            }
        ));
        jTableMesero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMeseroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMesero);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ingresar Nombre");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ingresar apellido");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ingresar DNI");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ingresar Usuario ");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ingresar Contraseña");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Seleccione Estado");

        jRActivo.setText("Activo");

        jRInactivo.setText("Inactivo");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tabla Meseros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRActivo)
                                .addGap(33, 33, 33)
                                .addComponent(jRInactivo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(jBActualizar))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTApellido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTDNI, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTContraseña, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRInactivo)
                            .addComponent(jRActivo))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBAgregar)
                            .addComponent(jBActualizar))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMeseroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMeseroMouseClicked
       int rowIndex = jTableMesero.getSelectedRow();
        if (rowIndex != -1) { // Asegúrate de que haya una fila seleccionada
            cargarDatosMeseroSeleccionado(rowIndex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTableMeseroMouseClicked

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        try {
        String nombre = jTNombre.getText();
        String apellido = jTApellido.getText();
        String dni = jTDNI.getText();
        String usuario = jTUsuario.getText();
        String contraseña = jTContraseña.getText();
        boolean estado = jRActivo.isSelected(); // suponiendo que jRActivo es el botón de selección para el estado

        Mesero nuevoMesero = new Mesero(nombre, apellido, dni, estado, usuario, contraseña);
        meseroData.agregarMesero(nuevoMesero); // Agrega el producto a la base de datos
        actualizarTabla(); // Actualiza la tabla después de agregar
        JOptionPane.showMessageDialog(this, "Mesero agregado exitosamente.");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para cantidad y precio.");
    }
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int filaSeleccionada = jTableMesero.getSelectedRow();
        if (filaSeleccionada != -1) 
        {
        int idMesero = (int) modelo.getValueAt(filaSeleccionada, 0);
        String nombre = jTNombre.getText();
        String apellido = jTApellido.getText();
        String dni = jTDNI.getText();
        String usuario = jTUsuario.getText();
        String contraseña = jTContraseña.getText();
        boolean estado = jRActivo.isSelected();

        Mesero nuevoMesero = new Mesero(idMesero, nombre, apellido, dni, estado, usuario, contraseña);

        meseroData.actualizarMesero(nuevoMesero);
        
        consultar(); 

        JOptionPane.showMessageDialog(this, "Mesero actualizado exitosamente.");
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un Mesero para actualizar.");
    }
    }//GEN-LAST:event_jBActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRActivo;
    private javax.swing.JRadioButton jRInactivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTContraseña;
    private javax.swing.JTextField jTDNI;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTUsuario;
    private javax.swing.JTable jTableMesero;
    // End of variables declaration//GEN-END:variables
}