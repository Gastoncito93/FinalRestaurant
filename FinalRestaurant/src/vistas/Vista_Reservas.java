
package vistas;

import entidades.Reserva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ReservaData;

public class Vista_Reservas extends javax.swing.JInternalFrame {
    
        private Connection connection;
        private ReservaData reservaData;
        private DefaultTableModel modelo;
        private ButtonGroup grupoEstado;
        
        private Map<String, Integer> mesaMap; // Para almacenar la relación número -> id_mesa

    public Vista_Reservas() {
        initComponents();
        conectarBaseDeDatos();
        reservaData = new ReservaData(connection);
        inicializarModelo();
        llenarComboBoxMesas();
        grupoEstado = new ButtonGroup();
        grupoEstado.add(jRadioButton1);
        grupoEstado.add(jRadioButton2);
        consultarReservasPorMesa();
        
    
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
        modelo.addColumn("ID Reserva");
        modelo.addColumn("ID Mesa");
        modelo.addColumn("Cliente");
        modelo.addColumn("DNI Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Estado");
        JTableReservas.setModel(modelo);
    }
    
private void llenarComboBoxMesas() {
    mesaMap = new HashMap<>(); // Inicializa el HashMap
    String sql = "SELECT id_mesa, numero FROM mesa";
    try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        jCMesas.removeAllItems();
        while (rs.next()) {
            int idMesa = rs.getInt("id_mesa");
            String numeroMesa = rs.getString("numero");
            jCMesas.addItem(numeroMesa);
            mesaMap.put(numeroMesa, idMesa);
        }
        if (jCMesas.getItemCount() > 0) {
            jCMesas.setSelectedIndex(0); // Seleccionar el primer elemento por defecto
            consultarReservasPorMesa();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar las mesas: " + e.getMessage());
    }

    // Añade un listener al JComboBox para actualizar la tabla cuando se seleccione una mesa
    jCMesas.addActionListener((java.awt.event.ActionEvent evt) -> {
        consultarReservasPorMesa();
    });
}



    private void consultarReservasPorMesa() {
        String mesaSeleccionada = (String) jCMesas.getSelectedItem();
    if (mesaSeleccionada != null) {
        int idMesa = mesaMap.get(mesaSeleccionada);
        List<Reserva> reservas = reservaData.obtenerReservasPorMesa(idMesa);
        modelo.setRowCount(0); // Limpiar el modelo de la tabla

        for (Reserva reserva : reservas) {
            Object[] fila = {
                reserva.getIdReserva(),
                reserva.getId_mesa(),
                reserva.getNombrePersona(),
                reserva.getDni(),
                reserva.getFecha(),
                reserva.getHora(),
                reserva.isEstado() ? "Vigente" : "No vigente"
            };
            modelo.addRow(fila);
        }

        JTableReservas.setModel(modelo);
        JTableReservas.revalidate(); // Validar la tabla después de actualizar el modelo
        JTableReservas.repaint(); // Repintar la tabla
    }
    }

    private void actualizarTablaReservas() {
        consultarReservasPorMesa();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTDni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCMesas = new javax.swing.JComboBox<>();
        jTFecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTHora = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableReservas = new javax.swing.JTable();
        jBAgregar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Reserva");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nº de mesa");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre del Cliente");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DNI del Cliente");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fecha");

        jCMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Hora");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Estado");

        jRadioButton1.setText("Activa");

        jRadioButton2.setText("Finalizada");

        JTableReservas.setModel(new javax.swing.table.DefaultTableModel(
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
        JTableReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableReservas);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(jBActualizar))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton2))
                            .addComponent(jCMesas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTNombre)
                            .addComponent(jTDni)
                            .addComponent(jTFecha)
                            .addComponent(jTHora)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBAgregar)
                            .addComponent(jBActualizar)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        try {
        String mesaSeleccionada = (String) jCMesas.getSelectedItem();
        int idMesa = mesaMap.get(mesaSeleccionada); // Obtener id_mesa del HashMap
        String nombre = jTNombre.getText();
        String dni = jTDni.getText();
        String fecha = jTFecha.getText(); // Asegúrate de que esté en formato YYYY-MM-DD
        String hora = jTHora.getText();   // Asegúrate de que esté en formato HH:MM:SS
        boolean estado = jRadioButton1.isSelected();

        // Verificar si la reserva ya existe
        List<Reserva> reservas = reservaData.listaDeReservas();
        boolean reservaExiste = false;
        for (Reserva reservasexistentes : reservas) {
            if (reservasexistentes.getId_mesa() == idMesa && reservasexistentes.getFecha().equalsIgnoreCase(fecha) && reservasexistentes.getHora().equalsIgnoreCase(hora)) {
                reservaExiste = true;
                break;
            }
        }

        if (reservaExiste) {
            JOptionPane.showMessageDialog(this, "Error: La reserva ya existe para la mesa seleccionada en la fecha y hora especificadas.");
        } else {
            Reserva nuevaReserva = new Reserva(idMesa, nombre, dni, fecha, hora, estado);
            reservaData.crearReserva(nuevaReserva);
            actualizarTablaReservas();
            JOptionPane.showMessageDialog(this, "Reserva agregada exitosamente.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa datos válidos.");
    }
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int fila = JTableReservas.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla para actualizar.");
    } else {
        try {
            int idReserva = (int) JTableReservas.getValueAt(fila, 0);
            int idMesa = mesaMap.get((String) jCMesas.getSelectedItem());
            String nombre = jTNombre.getText();
            String dni = jTDni.getText();
            String fecha = jTFecha.getText(); // Asegúrate de que esté en formato YYYY-MM-DD
            String hora = jTHora.getText();   // Asegúrate de que esté en formato HH:MM:SS
            boolean estado = jRadioButton1.isSelected();

            Reserva reservaActualizada = new Reserva(idReserva, idMesa, nombre, dni, fecha, hora, estado);
            reservaData.actualizarReserva(reservaActualizada);

            actualizarTablaReservas(); // Actualiza la tabla después de actualizar

            JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa datos válidos.");
        }
    }
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void JTableReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableReservasMouseClicked
        if (evt.getClickCount() == 1) { // Asegúrate de que el evento se maneje solo en un clic
        int fila = JTableReservas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No se seleccionó una fila");
        } else {
            int idReserva = Integer.parseInt(JTableReservas.getValueAt(fila, 0).toString());
            int idMesa = Integer.parseInt(JTableReservas.getValueAt(fila, 1).toString());
            String nombreCliente = JTableReservas.getValueAt(fila, 2).toString();
            String dniCliente = JTableReservas.getValueAt(fila, 3).toString();
            String fecha = JTableReservas.getValueAt(fila, 4).toString();
            String hora = JTableReservas.getValueAt(fila, 5).toString();
            String estadoStr = JTableReservas.getValueAt(fila, 6).toString();

            jTNombre.setText(nombreCliente);
            jTDni.setText(dniCliente);
            jTFecha.setText(fecha);
            jTHora.setText(hora);

            if (estadoStr.equals("Vigente")) {
                jRadioButton1.setSelected(true); // Vigente
            } else {
                jRadioButton2.setSelected(true); // No vigente
            }
 
         /*   for (int i = 0; i < jCMesas.getItemCount(); i++) {
                if (mesaMap.get(jCMesas.getItemAt(i)) == idMesa) {
                    jCMesas.setSelectedIndex(i);
                    break;
                }
            } */
        }
    }
    }//GEN-LAST:event_JTableReservasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableReservas;
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JComboBox<String> jCMesas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTDni;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JTextField jTHora;
    private javax.swing.JTextField jTNombre;
    // End of variables declaration//GEN-END:variables
}
