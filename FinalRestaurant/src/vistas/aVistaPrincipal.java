package vistas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.Timer;
import java.util.TimerTask;


public class aVistaPrincipal extends javax.swing.JFrame {

    private DefaultTableModel modeloMesas;
    private DefaultTableModel modeloMeseros;
    private DefaultTableModel modeloReservas;
    private Connection connection;
    
    public aVistaPrincipal() {
        initComponents();
        conectarBaseDeDatos();
        inicializarModeloReservas();
        inicializarModeloMeseros();
        inicializarModeloMesas();
        consultarMesas();
        consultarMeseros();
        consultarReservas();
        
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            
            public void run() {
                consultarMesas();
                consultarMeseros();
                consultarReservas();
            }
        }, 0, 5000); // 0 de retraso inicial, 5000 milisegundos de intervalo
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
    void consultarMesas() {
        String sql = "SELECT  numero, estado FROM mesa";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Object[] mesa = new Object[2];
            modeloMesas.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                mesa[0] = rs.getInt("numero");
                mesa[1] = rs.getInt("estado");
                modeloMesas.addRow(mesa);
            }

            jTMesas.setModel(modeloMesas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void consultarMeseros() {
        String sql = "SELECT  id_mesero, apellido FROM mesero WHERE estado >= 1";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Object[] mesa = new Object[2];
            modeloMeseros.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                mesa[0] = rs.getInt("id_mesero");
                mesa[1] = rs.getString("apellido");
                modeloMeseros.addRow(mesa);
            }

            jTMeseros.setModel(modeloMeseros);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void consultarReservas() {
        String sql = "SELECT reserva.hora, mesa.numero FROM reserva JOIN mesa ON reserva.id_mesa = mesa.id_mesa WHERE reserva.fecha = (SELECT CURDATE())";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Object[] mesa = new Object[2];
            modeloReservas.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

            while (rs.next()) {
                mesa[0] = rs.getTime("reserva.hora");
                mesa[1] = rs.getString("mesa.numero");
                modeloReservas.addRow(mesa);
            }

            jTReservas.setModel(modeloReservas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTReservas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTMesas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMeseros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        escritorio = new javax.swing.JDesktopPane();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMadministracion = new javax.swing.JMenu();
        jMbaseAdmin = new javax.swing.JMenuItem();
        jMProducto = new javax.swing.JMenuItem();
        jMagregarMesa = new javax.swing.JMenuItem();
        jMAgregar = new javax.swing.JMenu();
        jMagregarReservas = new javax.swing.JMenuItem();
        jMCargarOrden = new javax.swing.JMenuItem();
        jMreservas = new javax.swing.JMenu();
        jMreservasTotal = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));

        jTReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reservas"
            }
        ));
        jScrollPane2.setViewportView(jTReservas);

        jTMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mesas"
            }
        ));
        jTMesas.setGridColor(new java.awt.Color(255, 204, 51));
        jScrollPane3.setViewportView(jTMesas);

        jTMeseros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meseros"
            }
        ));
        jScrollPane1.setViewportView(jTMeseros);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mesas.png"))); // NOI18N
        jLabel1.setText("Mesas");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mesero1.png"))); // NOI18N
        jLabel2.setText("Meseros");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/servicio.png"))); // NOI18N
        jLabel3.setText("Reservas");

        escritorio.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                escritorioAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rotisería grupo 100.png"))); // NOI18N

        escritorio.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addContainerGap(486, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(escritorio))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(escritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jMadministracion.setText("Administracion");

        jMbaseAdmin.setText("Agregar Mesero");
        jMbaseAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMbaseAdminActionPerformed(evt);
            }
        });
        jMadministracion.add(jMbaseAdmin);

        jMProducto.setText("Agregar Producto");
        jMProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMProductoActionPerformed(evt);
            }
        });
        jMadministracion.add(jMProducto);

        jMagregarMesa.setText("Agregar Mesa");
        jMagregarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMagregarMesaActionPerformed(evt);
            }
        });
        jMadministracion.add(jMagregarMesa);

        jMenuBar1.add(jMadministracion);

        jMAgregar.setText("Agregar");
        jMAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAgregarActionPerformed(evt);
            }
        });

        jMagregarReservas.setText("Crear Pedidos");
        jMagregarReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMagregarReservasActionPerformed(evt);
            }
        });
        jMAgregar.add(jMagregarReservas);

        jMCargarOrden.setText("Crear Orden");
        jMCargarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCargarOrdenActionPerformed(evt);
            }
        });
        jMAgregar.add(jMCargarOrden);

        jMenuBar1.add(jMAgregar);

        jMreservas.setText("Reservas");

        jMreservasTotal.setText("Reservas Totales");
        jMreservasTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMreservasTotalActionPerformed(evt);
            }
        });
        jMreservas.add(jMreservasTotal);

        jMenuBar1.add(jMreservas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMagregarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagregarReservasActionPerformed
        for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
            if (frame instanceof Vista_AgregarPedidos) {
                frame.dispose();
                break;
            }
        } 
        
        Vista_AgregarPedidos Reservas = new Vista_AgregarPedidos();
            escritorio.add(Reservas);
            Reservas.setVisible(true);
        
    }//GEN-LAST:event_jMagregarReservasActionPerformed

    private void jMbaseAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMbaseAdminActionPerformed
       for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
            if (frame instanceof Vista_AgregarMesero) {
                frame.dispose();
                break;
            }
        }
        Vista_AgregarMesero Administracion = new Vista_AgregarMesero();
            escritorio.add(Administracion);
            Administracion.setVisible(true);
            // Limpiar los campos de texto
    }//GEN-LAST:event_jMbaseAdminActionPerformed

    private void jMreservasTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMreservasTotalActionPerformed
        for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
            if (frame instanceof Vista_Reservas) {
                frame.dispose();
                break;
            }
        }
        
        Vista_Reservas ReservasTotales = new Vista_Reservas();
            escritorio.add(ReservasTotales);
            ReservasTotales.setVisible(true);
    }//GEN-LAST:event_jMreservasTotalActionPerformed

    private void jMagregarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagregarMesaActionPerformed
       for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
        if (frame instanceof Vista_AgregarMesa) {
            frame.dispose();
            break;
        }
    }
        Vista_AgregarMesa vista_AgregarMesa = new Vista_AgregarMesa();
            escritorio.add(vista_AgregarMesa);
            vista_AgregarMesa.setVisible(true);
    }//GEN-LAST:event_jMagregarMesaActionPerformed

    private void jMProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMProductoActionPerformed
    for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
        if (frame instanceof Vista_AgregarProducto) {
            frame.dispose();
            break;
        }
    }
    Vista_AgregarProducto vista_AgregarProducto = new Vista_AgregarProducto();
    escritorio.add(vista_AgregarProducto);
    vista_AgregarProducto.setVisible(true);     
    }//GEN-LAST:event_jMProductoActionPerformed

    private void jMCargarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCargarOrdenActionPerformed
      for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
        if (frame instanceof Vista_CargarOrden) {
            frame.dispose();
            break;
        }
    }
    Vista_CargarOrden vista_CargarOrden = new Vista_CargarOrden();
    escritorio.add(vista_CargarOrden);
    vista_CargarOrden.setVisible(true);
    }//GEN-LAST:event_jMCargarOrdenActionPerformed

    private void jMAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAgregarActionPerformed
        
        
        
    }//GEN-LAST:event_jMAgregarActionPerformed

    private void escritorioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_escritorioAncestorAdded

    }//GEN-LAST:event_escritorioAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(aVistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aVistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aVistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aVistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new aVistaPrincipal().setVisible(true);
            }
        });
    }
    private void inicializarModeloMesas() {
        modeloMesas = new DefaultTableModel();
        modeloMesas.addColumn("Mesa Nº");
        modeloMesas.addColumn("Estado");
        jTMesas.setModel(modeloMesas);
    }
    private void inicializarModeloMeseros() {
        modeloMeseros = new DefaultTableModel();
        modeloMeseros.addColumn("Id");
        modeloMeseros.addColumn("Apellido");
        jTMeseros.setModel(modeloMeseros);
    }
    
    private void inicializarModeloReservas() {
        modeloReservas = new DefaultTableModel();
        modeloReservas.addColumn("Horas");
        modeloReservas.addColumn("Mesa Nº");
        jTReservas.setModel(modeloReservas);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMAgregar;
    private javax.swing.JMenuItem jMCargarOrden;
    private javax.swing.JMenuItem jMProducto;
    private javax.swing.JMenu jMadministracion;
    private javax.swing.JMenuItem jMagregarMesa;
    private javax.swing.JMenuItem jMagregarReservas;
    private javax.swing.JMenuItem jMbaseAdmin;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMreservas;
    private javax.swing.JMenuItem jMreservasTotal;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTMesas;
    private javax.swing.JTable jTMeseros;
    private javax.swing.JTable jTReservas;
    // End of variables declaration//GEN-END:variables
}
