package vistas;

public class aVistaPrincipal extends javax.swing.JFrame {


    public aVistaPrincipal() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        escritorio = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mesa", "Mozo", "Reserva"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        escritorio.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(657, Short.MAX_VALUE))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
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
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
