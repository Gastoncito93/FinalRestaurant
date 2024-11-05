package vistas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        String sql = "SELECT  id_mesero, apellido FROM mesero";
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
        String sql = "SELECT reserva.hora, mesa.numero FROM reserva JOIN mesa ON reserva.id_mesa = mesa.id_mesa;";
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
        escritorio = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMeseros = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTReservas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTMesas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMadministracion = new javax.swing.JMenu();
        jMbaseAdmin = new javax.swing.JMenuItem();
        jMProducto = new javax.swing.JMenuItem();
        jMagregarMesa = new javax.swing.JMenuItem();
        jMAgregar = new javax.swing.JMenu();
        jMagregarReservas = new javax.swing.JMenuItem();
        jMreservas = new javax.swing.JMenu();
        jMreservasTotal = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                escritorioAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTMeseros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Meseros"
            }
        ));
        jScrollPane1.setViewportView(jTMeseros);

        jTReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Reservas"
            }
        ));
        jScrollPane2.setViewportView(jTReservas);

        jTMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Mesas"
            }
        ));
        jScrollPane3.setViewportView(jTMesas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mesas");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Meseros");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reservas");

        escritorio.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addContainerGap(286, Short.MAX_VALUE)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
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

        jMagregarReservas.setText("Agregar Pedidos");
        jMagregarReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMagregarReservasActionPerformed(evt);
            }
        });
        jMAgregar.add(jMagregarReservas);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMagregarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagregarReservasActionPerformed
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
    }//GEN-LAST:event_jMbaseAdminActionPerformed

    private void jMreservasTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMreservasTotalActionPerformed
        Vista_Reservas ReservasTotales = new Vista_Reservas();
            escritorio.add(ReservasTotales);
            ReservasTotales.setVisible(true);
    }//GEN-LAST:event_jMreservasTotalActionPerformed

    private void escritorioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_escritorioAncestorAdded
      
        
    }//GEN-LAST:event_escritorioAncestorAdded

    private void jMagregarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagregarMesaActionPerformed
       for (javax.swing.JInternalFrame frame : escritorio.getAllFrames()) {
        if (frame instanceof Vista_AgregarMesa) {
            frame.dispose();
            break;
        }
    }
        
        Vista_AgregarMesa vista_AgregarMesa = new Vista_AgregarMesa();
            escritorio.add(vista_AgregarMesa);
            vista_AgregarMesa.setVisible(true);        // TODO add your handling code here:
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
        modeloReservas.addColumn("Horario de la Reserva");
        modeloReservas.addColumn("N° de Mesa");
        jTReservas.setModel(modeloReservas);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMAgregar;
    private javax.swing.JMenuItem jMProducto;
    private javax.swing.JMenu jMadministracion;
    private javax.swing.JMenuItem jMagregarMesa;
    private javax.swing.JMenuItem jMagregarReservas;
    private javax.swing.JMenuItem jMbaseAdmin;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMreservas;
    private javax.swing.JMenuItem jMreservasTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTMesas;
    private javax.swing.JTable jTMeseros;
    private javax.swing.JTable jTReservas;
    // End of variables declaration//GEN-END:variables
}
