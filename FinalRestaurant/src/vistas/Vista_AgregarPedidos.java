package vistas;

import conexion.Conexion;
import entidades.Mesa;
import entidades.Mesero;
import entidades.Pedido;
import entidades.Reserva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.MesaData;
import persistencia.MeseroData;
import persistencia.PedidoData;
import persistencia.ReservaData;

/**
 *
 * @author Lenovo
 */
public class Vista_AgregarPedidos extends javax.swing.JInternalFrame {

    private List<Mesa> mesas; // Lista para almacenar las mesas
    private Connection connection;
    private MesaData mesaData;
    private ReservaData reservaData;
    private MeseroData meseroData;
    private PedidoData pedidoData;
    private DefaultTableModel modelo;
    private Map<String, Integer> mesaMap; // Para almacenar la relación número -> id_mesa

    /**
     * Creates new form Vista_AgregarReservas
     */
    public Vista_AgregarPedidos() {
        conectarBaseDeDatos();
        mesaData = new MesaData(connection);
        reservaData = new ReservaData(connection);
        meseroData = new MeseroData(connection);
        pedidoData = new PedidoData(connection);
        initComponents();
        inicializarModelo();
        cargarMesasEnCombo();
        cargarMeserosEnCombo();
        consultarPedidosPorMesa();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBMesas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jCBMeseros = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jBGenerarPedido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPedido = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Crear Pedido");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mesas Disponibles");

        jCBMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBMesas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBMesasItemStateChanged(evt);
            }
        });
        jCBMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBMesasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Meseros Disponibles");

        jCBMeseros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Estado");

        jBGenerarPedido.setText("Generar Pedido");
        jBGenerarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGenerarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCBMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jCBMeseros, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBGenerarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBMeseros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBGenerarPedido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTPedido.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTPedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jBGenerarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGenerarPedidoActionPerformed
        Integer mesaSeleccionada = (Integer) jCBMesas.getSelectedItem();
        String meseroSeleccionado = (String) jCBMeseros.getSelectedItem();
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        String fechaCompleta = fechaActual.toString() + " " + horaActual.toString();

        // Obtener IDs de la mesa y del mesero
        int idMesa = -1;
        for (Mesa m : mesas) {
            if (m.getNumero() == mesaSeleccionada) {
                idMesa = m.getIdMesa();
                break;
            }
        }

        int idMesero = -1;
        List<Mesero> meseros = meseroData.obtenerMeseros();
        for (Mesero mesero : meseros) {
            if (mesero.getApellido().equals(meseroSeleccionado)) {
                idMesero = mesero.getIdMesero();
                break;
            }
        }

        // Verificar si ya existe un pedido activo en la mesa
        PedidoData pedidoData = new PedidoData(connection);  // Asegúrate de tener la conexión inicializada
        if (pedidoData.existePedidoActivoEnMesa(idMesa)) {
            JOptionPane.showMessageDialog(this, "Ya existe un pedido activo para esta mesa.");
            return;  // Salir del método sin crear un nuevo pedido
        }

        // Crear el pedido y guardarlo en la base de datos
        String sql = "INSERT INTO pedido (id_mesa, id_mesero, fecha, estado) VALUES (?, ?, ?, 1)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idMesa);
            pstmt.setInt(2, idMesero);
            pstmt.setString(3, fechaCompleta);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pedido generado exitosamente.");

            // Actualizar la tabla con el nuevo pedido
            consultarPedidosPorMesa();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el pedido: " + e.getMessage());
        }

    }//GEN-LAST:event_jBGenerarPedidoActionPerformed

    private void jCBMesasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBMesasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBMesasItemStateChanged

    private void jCBMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBMesasActionPerformed
        consultarPedidosPorMesa();
    }//GEN-LAST:event_jCBMesasActionPerformed

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

    /* private void llenarComboBoxMesas() {
    mesaMap = new HashMap<>(); // Inicializa el HashMap
    String sql = "SELECT id_mesa, numero FROM mesa";
    try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
        jCBMesas.removeAllItems();
        while (rs.next()) {
            int idMesa = rs.getInt("id_mesa");
            String numeroMesa = rs.getString("numero");
            jCBMesas.addItem(numeroMesa);
            mesaMap.put(numeroMesa, idMesa);
        }
        if (jCBMesas.getItemCount() > 0) {
            jCBMesas.setSelectedIndex(0); // Seleccionar el primer elemento por defecto
            consultarReservasPorMesa();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar las mesas: " + e.getMessage());
    }

    // Añade un listener al JComboBox para actualizar la tabla cuando se seleccione una mesa
    jCBMesas.addActionListener((java.awt.event.ActionEvent evt) -> {
        consultarReservasPorMesa();
    });
}
    
    private void consultarReservasPorMesa() {
        String mesaSeleccionada = (String) jCBMesas.getSelectedItem();
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

        jTPedido.setModel(modelo);
        jTPedido.revalidate(); // Validar la tabla después de actualizar el modelo
        jTPedido.repaint(); // Repintar la tabla
    }
    }*/
    private void cargarMesasEnCombo() {
        mesas = new ArrayList<>();
        jCBMesas.removeAllItems();
        List<Mesa> mesasDisponibles = mesaData.obtenerMesas();
        for (Mesa m : mesasDisponibles) {
            if (m.isEstado()) {
                jCBMesas.addItem(m.getNumero());
                mesas.add(m); //añadimos la mesa a la lista
            }
        }
    }

    private void cargarMeserosEnCombo() {
        jCBMeseros.removeAllItems();
        List<Mesero> meseros = meseroData.obtenerMeseros();
        for (Mesero mesero : meseros) {
            if (mesero.isEstado()) {
                jCBMeseros.addItem(mesero.getApellido());
            }
        }
    }

    private void inicializarModelo() {
        modelo = new DefaultTableModel();
        modelo.addColumn("N° De Pedido");
        modelo.addColumn("N° Mesa");
        modelo.addColumn("Mesero");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");
        jTPedido.setModel(modelo);
    }

    private void consultarPedidosPorMesa() {
        Integer mesaSeleccionada = (Integer) jCBMesas.getSelectedItem(); // Asegúrate de que jCBMesas maneja Integer
        if (mesaSeleccionada != null) {
            int idMesa = -1;
            for (Mesa m : mesas) {
                if (m.getNumero() == mesaSeleccionada) {
                    idMesa = m.getIdMesa();
                    break;
                }
            }

            List<Pedido> pedidos = pedidoData.obtenerPedidos(); // Ajustar si es necesario para obtener pedidos por mesa
            modelo.setRowCount(0); // Limpiar el modelo de la tabla

            for (Pedido pedido : pedidos) {
                if (pedido.getIdMesa() == idMesa) {
                    // Obtener el número de la mesa
                    String numeroMesa = "";
                    for (Mesa m : mesas) {
                        if (m.getIdMesa() == pedido.getIdMesa()) {
                            numeroMesa = String.valueOf(m.getNumero());
                            break;
                        }
                    }

                    // Obtener el apellido del mesero
                    String apellidoMesero = "";
                    List<Mesero> meseros = meseroData.obtenerMeseros();
                    for (Mesero mesero : meseros) {
                        if (mesero.getIdMesero() == pedido.getIdMesero()) {
                            apellidoMesero = mesero.getApellido();
                            break;
                        }
                    }

                    // Ahora agregamos el id_pedido al arreglo de fila
                    Object[] fila = {
                        pedido.getIdPedido(), // Agregar el id_pedido
                        numeroMesa,
                        apellidoMesero,
                        pedido.getFecha(),
                        pedido.isEstado() ? "1" : "0"
                    };
                    modelo.addRow(fila);
                }
            }

            jTPedido.setModel(modelo);
            jTPedido.revalidate(); // Validar la tabla después de actualizar el modelo
            jTPedido.repaint(); // Repintar la tabla
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGenerarPedido;
    private javax.swing.JComboBox<Object> jCBMesas;
    private javax.swing.JComboBox<Object> jCBMeseros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTPedido;
    // End of variables declaration//GEN-END:variables
}
