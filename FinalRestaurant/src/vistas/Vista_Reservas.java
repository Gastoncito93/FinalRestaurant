package vistas;

import entidades.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ReservaData;
import java.time.LocalDate;
import java.time.ZoneId;

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
        llenarComboBoxHora();
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
        String sql = "SELECT id_mesa, numero FROM mesa WHERE estado >= 1";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            jCMesas.removeAllItems(); // Limpiar el JComboBox antes de añadir los items
            while (rs.next()) {
                int idMesa = rs.getInt("id_mesa");
                String numeroMesa = rs.getString("numero");
                jCMesas.addItem(numeroMesa);
                mesaMap.put(numeroMesa, idMesa); // Relaciona número de mesa con ID
            }
            if (jCMesas.getItemCount() > 0) {
                jCMesas.setSelectedIndex(0); // Seleccionar el primer elemento por defecto
                consultarReservasPorMesa();  // Llamar a la función para consultar las reservas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las mesas: " + e.getMessage());
        }

        // Añadir listener al JComboBox para actualizar la tabla cuando se seleccione una mesa
        jCMesas.addActionListener((java.awt.event.ActionEvent evt) -> {
            System.out.println("Mesa seleccionada: " + jCMesas.getSelectedItem());
            consultarReservasPorMesa();
        });
    }

    private void llenarComboBoxHora() {
        jCBHora.removeAllItems();
        jCBHora.addItem("19:00");
        jCBHora.addItem("20:00");
        jCBHora.addItem("21:00");
        jCBHora.addItem("22:00");
        jCBHora.addItem("23:00");
        jCBHora.addItem("00:00");
    }

    private void consultarReservasPorMesa() {
        String mesaSeleccionada = (String) jCMesas.getSelectedItem();
        if (mesaSeleccionada != null) {
            int idMesa = mesaMap.get(mesaSeleccionada);
            List<Reserva> reservas = reservaData.obtenerReservasPorMesa(idMesa);
            modelo.setRowCount(0); // Limpiar el modelo de la tabla

            for (Reserva reserva : reservas) {
                // Verificar si la fecha y la hora son null
                String fechaStr = (reserva.getFecha() != null) ? reserva.getFecha().toString() : "N/A";
                String horaStr = (reserva.getHora() != null) ? reserva.getHora().toString() : "N/A";

                Object[] fila = {
                    reserva.getIdReserva(),
                    reserva.getId_mesa(),
                    reserva.getNombrePersona(),
                    reserva.getDni(),
                    fechaStr, // Mostrar la fecha como String
                    horaStr, // Mostrar la hora como String
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableReservas = new javax.swing.JTable();
        jBAgregar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jDCFecha = new com.toedter.calendar.JDateChooser();
        jCBHora = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reserva1.png"))); // NOI18N
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
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/estado.png"))); // NOI18N
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

        jBAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregaboton2.png"))); // NOI18N
        jBAgregar.setText("Agregar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jBActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar1.png"))); // NOI18N
        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });

        jCBHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDCFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBHora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAgregar)
                    .addComponent(jBActualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        if (jCMesas.getSelectedItem() != null
                && !jTNombre.getText().isEmpty()
                && !jTDni.getText().isEmpty()
                && jDCFecha.getDate() != null
                && jCBHora.getSelectedItem() != null) {

            // Convertir LocalDate.now() a java.util.Date
            java.util.Date fechaActual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Comparar si la fecha seleccionada es posterior a la actual
            if (!jDCFecha.getDate().before(fechaActual)) {

                try {
                    // Obtener los datos del formulario
                    String mesaSeleccionada = (String) jCMesas.getSelectedItem();
                    int idMesa = mesaMap.get(mesaSeleccionada); // Mapa que relaciona nombres de mesas con IDs
                    String nombre = jTNombre.getText();
                    String dni = jTDni.getText();

                    // Obtener la fecha seleccionada de jDCFecha
                    java.util.Date fechaSeleccionada = jDCFecha.getDate(); // Obtiene java.util.Date

                    if (fechaSeleccionada != null) {
                        // Convertir java.util.Date a java.sql.Date
                        java.sql.Date sqlFecha = new java.sql.Date(fechaSeleccionada.getTime()); // Conversión correcta

                        // Convertir la hora seleccionada en String a LocalTime
                        String horaSeleccionada = (String) jCBHora.getSelectedItem();
                        LocalTime hora = LocalTime.parse(horaSeleccionada);

                        boolean estado = jRadioButton1.isSelected(); // Estado de la reserva

                        // Crear la reserva con LocalDate y LocalTime
                        Reserva nuevaReserva = new Reserva(idMesa, nombre, dni, sqlFecha.toLocalDate(), hora, estado);
                        reservaData.crearReserva(nuevaReserva); // Guardar la reserva en la base de datos

                        // Mostrar mensaje de confirmación
                        JOptionPane.showMessageDialog(this, "Se creó una reserva para el día "
                                + nuevaReserva.getFecha() + " a la hora " + nuevaReserva.getHora());

                        // Actualizar la vista con las reservas
                        consultarReservasPorMesa();
                    } else {
                        JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha.");
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingresa datos válidos.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una fecha posterior al día actual.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa todos los datos.");
        }
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int fila = JTableReservas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla para actualizar.");
        } else {
            try {
                // Obtener el ID de la reserva seleccionada
                int idReserva = (int) JTableReservas.getValueAt(fila, 0);

                // Obtener el ID de la mesa seleccionada desde el ComboBox
                int idMesa = mesaMap.get((String) jCMesas.getSelectedItem());

                // Obtener el nombre y el DNI desde los campos de texto
                String nombre = jTNombre.getText();
                String dni = jTDni.getText();

                // Obtener la fecha seleccionada desde el JDateChooser
                java.util.Date fechaSeleccionada = jDCFecha.getDate(); // Obtiene java.util.Date
                if (fechaSeleccionada == null) {
                    JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha.");
                    return;
                }

                // Convertir la java.util.Date a java.sql.Date
                java.sql.Date sqlFecha = new java.sql.Date(fechaSeleccionada.getTime()); // Conversión correcta

                // Convertir la hora seleccionada a LocalTime
                String horaSeleccionada = (String) jCBHora.getSelectedItem();
                LocalTime hora = LocalTime.parse(horaSeleccionada);

                // Obtener el estado de la reserva (por ejemplo, si está activa o no)
                boolean estado = jRadioButton1.isSelected();

                // Crear el objeto Reserva actualizado con LocalDate y LocalTime
                Reserva reservaActualizada = new Reserva(idReserva, idMesa, nombre, dni, sqlFecha.toLocalDate(), hora, estado);
                // Convertir LocalDate.now() a java.util.Date
                java.util.Date fechaActual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

                // Comparar si la fecha seleccionada es posterior a la actual
                if (!jDCFecha.getDate().before(fechaActual)) {
                    // Llamar al método para actualizar la reserva en la base de datos
                    reservaData.actualizarReserva(reservaActualizada);

                    // Actualizar la tabla con las nuevas reservas
                    actualizarTablaReservas();

                    // Mostrar un mensaje de confirmación
                    JOptionPane.showMessageDialog(this, "Reserva actualizada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione una fecha posterior al día actual.");
                }
            } catch (Exception e) {
                // Manejo de errores en caso de que algo no esté correcto
                JOptionPane.showMessageDialog(this, "Por favor, ingresa datos válidos.");
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jBActualizarActionPerformed

    private void JTableReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableReservasMouseClicked
        if (evt.getClickCount() == 1) { // Asegúrate de que el evento se maneje solo en un clic
            int fila = JTableReservas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "No se seleccionó una fila");
            } else {
                // Obtén los valores de la fila seleccionada
                int idReserva = Integer.parseInt(JTableReservas.getValueAt(fila, 0).toString());
                int idMesa = Integer.parseInt(JTableReservas.getValueAt(fila, 1).toString());
                String nombreCliente = JTableReservas.getValueAt(fila, 2).toString();
                String dniCliente = JTableReservas.getValueAt(fila, 3).toString();
                String fechaStr = JTableReservas.getValueAt(fila, 4).toString();
                String horaStr = JTableReservas.getValueAt(fila, 5).toString();
                String estadoStr = JTableReservas.getValueAt(fila, 6).toString();

                // Setea los valores de los campos de texto
                jTNombre.setText(nombreCliente);
                jTDni.setText(dniCliente);

                // Convierte la fecha String a LocalDate y asigna a JDateChooser
                try {
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    jDCFecha.setDate(Date.valueOf(fecha)); // Asignamos la fecha al JDateChooser
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto");
                }

                // Asigna la hora al JComboBox de horas
                jCBHora.setSelectedItem(horaStr);

                // Establecer el estado en el radioButton
                if (estadoStr.equals("Vigente")) {
                    jRadioButton1.setSelected(true); // Vigente
                } else {
                    jRadioButton2.setSelected(true); // No vigente
                }

                // Puedes descomentar el siguiente código si deseas asignar la mesa al JComboBox
                /*for (int i = 0; i < jCMesas.getItemCount(); i++) {
            if (mesaMap.get(jCMesas.getItemAt(i)) == idMesa) {
                jCMesas.setSelectedIndex(i);
                break;
            }
        }*/
            }
        }

    }//GEN-LAST:event_JTableReservasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableReservas;
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JComboBox<String> jCBHora;
    private javax.swing.JComboBox<String> jCMesas;
    private com.toedter.calendar.JDateChooser jDCFecha;
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
    private javax.swing.JTextField jTNombre;
    // End of variables declaration//GEN-END:variables
}
