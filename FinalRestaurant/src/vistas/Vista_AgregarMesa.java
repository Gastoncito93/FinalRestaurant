package vistas;

import entidades.Mesa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.MesaData;

public class Vista_AgregarMesa extends javax.swing.JInternalFrame {

    private ButtonGroup grupoEstado;
    private Connection connection;
    MesaData mesaData = new MesaData(connection);
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;

    public Vista_AgregarMesa() {
        initComponents();
        conectarBaseDeDatos(); // Primero, establece la conexión
        mesaData = new MesaData(connection); // Luego, inicializa MesaData
        inicializarModelo();
        grupoEstado = new ButtonGroup();
        grupoEstado.add(jRDeshabilitada);
        grupoEstado.add(jRHabilitada);
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

    void consultar() {
        String sql = "SELECT id_mesa, numero, capacidad, estado FROM mesa";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Object[] mesa = new Object[4];
            modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                mesa[0] = rs.getInt("id_mesa");
                mesa[1] = rs.getInt("numero");
                mesa[2] = rs.getInt("capacidad");
                mesa[3] = rs.getBoolean("estado");
                modelo.addRow(mesa);
            }

            jTMesa.setModel(modelo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    private void setearTodoEnVacio(){
        // Limpiar los campos de texto
        jTextFieldNumero.setText("");
        jTextFieldCapacidad.setText("");
        jRHabilitada.setSelected(false);
    }
    
    private void inicializarModelo() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Mesa N°");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Estado");
        jTMesa.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCapacidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMesa = new javax.swing.JTable();
        jBAgregar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jBBorrar = new javax.swing.JButton();
        jRHabilitada = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jRDeshabilitada = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear Mesas");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Numero de mesa:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Capacidad de mesa:");

        jTMesa.setModel(new javax.swing.table.DefaultTableModel(
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
        jTMesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTMesaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTMesa);

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

        jBBorrar.setText("Borrar");
        jBBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBorrarActionPerformed(evt);
            }
        });

        jRHabilitada.setText("Habilitada");
        jRHabilitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRHabilitadaActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Estado de mesa:");

        jRDeshabilitada.setText("Deshabilitada");
        jRDeshabilitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRDeshabilitadaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Control de Mesas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBAgregar)
                        .addGap(20, 20, 20)
                        .addComponent(jBActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jBBorrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jRHabilitada)
                                .addGap(5, 5, 5)
                                .addComponent(jRDeshabilitada))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldNumero)
                                .addComponent(jTextFieldCapacidad)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRHabilitada)
                            .addComponent(jRDeshabilitada))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBBorrar)
                            .addComponent(jBActualizar)
                            .addComponent(jBAgregar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRDeshabilitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRDeshabilitadaActionPerformed

    }//GEN-LAST:event_jRDeshabilitadaActionPerformed

    private void jRHabilitadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRHabilitadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRHabilitadaActionPerformed

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        try {
        int numero = Integer.parseInt(jTextFieldNumero.getText());
        int capacidad = Integer.parseInt(jTextFieldCapacidad.getText());
        boolean estado = jRHabilitada.isSelected();

        // Verificar si la mesa ya existe antes de agregarla
        if (mesaData.mesaExiste(numero)) {
            JOptionPane.showMessageDialog(this, "El número de mesa ya está en uso. Elige otro número.");
            return;
        }

        Mesa nuevaMesa = new Mesa(numero, capacidad, estado, null);
        mesaData.agregarMesa(nuevaMesa); // Agrega la mesa a la base de datos
        consultar(); // Actualiza la tabla después de agregar
        setearTodoEnVacio(); // Limpiar campos de entrada

        JOptionPane.showMessageDialog(this, "Mesa agregada exitosamente.");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para número y capacidad.");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al agregar la mesa: " + e.getMessage());
    }
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        int filaSeleccionada = jTMesa.getSelectedRow();
    if (filaSeleccionada != -1) {
        int idMesa = (int) modelo.getValueAt(filaSeleccionada, 0); // Obtener el ID de la mesa seleccionada

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas borrar esta mesa?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            mesaData.eliminarMesa(idMesa); // Llama al método para eliminar la mesa
            consultar(); // Actualiza la tabla
            JOptionPane.showMessageDialog(this, "Mesa borrada exitosamente.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una mesa para borrar.");
    }
    setearTodoEnVacio();
    }//GEN-LAST:event_jBBorrarActionPerformed

    private void jTMesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTMesaMouseClicked
        int rowIndex = jTMesa.getSelectedRow();
        if (rowIndex != -1) { // Asegúrate de que haya una fila seleccionada
            cargarDatosMesaSeleccionada(rowIndex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTMesaMouseClicked

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int filaSeleccionada = jTMesa.getSelectedRow();
    if (filaSeleccionada != -1) {
        int numero = Integer.parseInt(jTextFieldNumero.getText());
        int capacidad = Integer.parseInt(jTextFieldCapacidad.getText());
        boolean estado = jRHabilitada.isSelected(); 
        int idMesa = (int) modelo.getValueAt(filaSeleccionada, 0); 

        try {
            // Verificar si el número de mesa ya está en uso por otra mesa
            if (mesaData.mesaExiste(numero, idMesa)) {
                JOptionPane.showMessageDialog(this, "El número de mesa ya está en uso. Elige otro número.");
                return;
            }

            // Procede con la actualización si el número no está duplicado
            Mesa mesaActualizada = new Mesa(idMesa, numero, capacidad, estado, null); 
            mesaData.actualizarMesa(mesaActualizada);

            consultar(); // Actualiza la tabla después de actualizar
            setearTodoEnVacio(); // Limpiar campos de entrada

            JOptionPane.showMessageDialog(this, "Mesa actualizada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la mesa: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para número y capacidad.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una mesa para actualizar.");
    }
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void cargarDatosMesaSeleccionada(int rowIndex) {
    // Obtén los datos de la mesa seleccionada
    int idMesa = (int) modelo.getValueAt(rowIndex, 0); // El ID está en la primera columna
    int numero = (int) modelo.getValueAt(rowIndex, 1); // Número en la segunda columna
    int capacidad = (int) modelo.getValueAt(rowIndex, 2); // Capacidad en la tercera columna
    boolean estado = (boolean) modelo.getValueAt(rowIndex, 3); // Estado en la cuarta columna

    // Establece los valores en los campos de texto
    jTextFieldNumero.setText(String.valueOf(numero));
    jTextFieldCapacidad.setText(String.valueOf(capacidad));

    // Establece el estado en los radio buttons
    if (estado) {
        jRHabilitada.setSelected(true);
        jRDeshabilitada.setSelected(false);
    } else {
        jRHabilitada.setSelected(false);
        jRDeshabilitada.setSelected(true);
    }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jBBorrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRDeshabilitada;
    private javax.swing.JRadioButton jRHabilitada;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTMesa;
    private javax.swing.JTextField jTextFieldCapacidad;
    private javax.swing.JTextField jTextFieldNumero;
    // End of variables declaration//GEN-END:variables
}
