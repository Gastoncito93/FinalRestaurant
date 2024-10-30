/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

/**
 *
 * @author Usuario
 */
public class aVistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form aVistaPrincipal
     */
    public aVistaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMadministracion = new javax.swing.JMenu();
        jMbaseAdmin = new javax.swing.JMenuItem();
        jMAgregar = new javax.swing.JMenu();
        jMagregarReservas = new javax.swing.JMenuItem();
        jMreservas = new javax.swing.JMenu();
        jMreservasTotal = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                escritorioAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        jMadministracion.setText("Administracion");

        jMbaseAdmin.setText("Base de Administracion");
        jMbaseAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMbaseAdminActionPerformed(evt);
            }
        });
        jMadministracion.add(jMbaseAdmin);

        jMenuBar1.add(jMadministracion);

        jMAgregar.setText("Agregar");

        jMagregarReservas.setText("Agregar Reservas");
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
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMagregarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagregarReservasActionPerformed
         Vista_AgregarReservas Reservas = new Vista_AgregarReservas();
            escritorio.add(Reservas);
            Reservas.setVisible(true);
        
    }//GEN-LAST:event_jMagregarReservasActionPerformed

    private void jMbaseAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMbaseAdminActionPerformed
       Vista_Administracion Administracion = new Vista_Administracion();
            escritorio.add(Administracion);
            Administracion.setVisible(true);
        
        
    }//GEN-LAST:event_jMbaseAdminActionPerformed

    private void jMreservasTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMreservasTotalActionPerformed
        Vista_ReservasTotal ReservasTotales = new Vista_ReservasTotal();
            escritorio.add(ReservasTotales);
            ReservasTotales.setVisible(true);
    }//GEN-LAST:event_jMreservasTotalActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMAgregar;
    private javax.swing.JMenu jMadministracion;
    private javax.swing.JMenuItem jMagregarReservas;
    private javax.swing.JMenuItem jMbaseAdmin;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMreservas;
    private javax.swing.JMenuItem jMreservasTotal;
    // End of variables declaration//GEN-END:variables
}
