/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectoclienteservidor;
import conection.Conection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author dilmergo
 */
public class descuentos extends javax.swing.JFrame {
    
    private int idDescu;
    private int porceDescu;

    public descuentos(int idDescu, int porceDescu) {
        this.idDescu = idDescu;
        this.porceDescu = porceDescu;
    }

    public int getIdDescu() {
        return idDescu;
    }

    public void setIdDescu(int idDescu) {
        this.idDescu = idDescu;
    }

    public int getPorceDescu() {
        return porceDescu;
    }

    public void setPorceDescu(int porceDescu) {
        this.porceDescu = porceDescu;
    }

    
    
    
    
    ArrayList<descuentos> descuen = new ArrayList<>();
    
    Main main = new Main();
    ResultSet rs;
    int idc = 0;
    PreparedStatement pst;
    Conection conexion = new Conection(); 	
    Connection connection = conexion.getConexion();
    DefaultTableModel model;
    Statement st;
    /**
     * Creates new form MainView
     */
    public descuentos() {
        initComponents();
        setLocationRelativeTo(null);
        retrieve();
    }
    
    
    void limpiartabla(){
        for(int i = 0;i<informacion.getRowCount();i++){
            model.removeRow(i);
            i = 1-1;
        }
    }
    
    void limpiar(){
        codigodescuento.setText("");
        porcentajedescu.setText("");
        codigodescuento.requestFocus();
    }
    
    void retrieve(){
        String query = "select * from descuentos";
        
        try {
            connection = conexion.getConexion();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            Object[] descuentos = new Object[2];
            model = (DefaultTableModel) informacion.getModel();
            while (rs.next()) {
                descuentos[0] = rs.getInt("codigodescuento");
                descuentos[1] = rs.getString("porcentajedescuento");
                
                for(int i = 0;i<descuen.size();i++){
                    descuentos des = new descuentos((int) descuentos[0], Integer.parseInt((String) descuentos[1]));
                    descuen.add(des);
                }
                
                model.addRow(descuentos);
            }
            informacion.setModel(model);
        } catch (Exception e){
            System.out.println("Error while retrieving data: "+ e.getMessage());
        }
        finally {
            //Esto nos limpia los resultados obtenidos al ejecutar la consulta
            if(rs != null)
            {
                try
                {
                    rs.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
            //Vamos a limpiar la memoria destinada para la consulta
            if(pst != null)
            {
                try
                {
                    pst.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
            //Vamos a cerrar la conexión
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
        }
    }
    
    void agregardescuento(){     
        String codigo = codigodescuento.getText();
        String porcentaje = porcentajedescu.getText();
       
        try {
            if (codigo.equals("") || porcentaje.equals("")){
                JOptionPane.showMessageDialog(null, "Hay campos sin datos");
                limpiartabla();
            }
            else {
                String query = "insert into descuentos(codigodescuento, porcentajedescuento)" +
                        "values ('"+ codigo + "', '" + porcentaje +"')";
                
                connection = conexion.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "producto descuento exitosamente");
                limpiartabla();
            }
        } catch (Exception e){
            System.out.println("Error while adding data: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar descuento");
            limpiartabla();
        }
        finally {
            //Vamos a limpiar la memoria destinada para la consulta
            if(pst != null)
            {
                try
                {
                    pst.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
            //Vamos a cerrar la conexión
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
        }
    }
    
    void editardescuento() {
        String codigo = codigodescuento.getText();
        String porcentaje = porcentajedescu.getText();
        
       
        try {
            if(codigo.equals("")||
               porcentaje.equals("")){
               JOptionPane.showMessageDialog(null, "hay espacios vacios");
               limpiartabla();
            }
            else {
                String query = "update descuentos set codigodescuento='"+ codigo + "', porcentajedescuento='" + porcentaje + "' where id=" + idc;
                connection = conexion.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "el descuento fue modificado");
                limpiartabla();
            }
        } catch (Exception e){
            System.out.println("Error while adding data: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar descuento");
            limpiartabla();
        }
        finally {
            //Vamos a limpiar la memoria destinada para la consulta
            if(pst != null)
            {
                try
                {
                    pst.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
            //Vamos a cerrar la conexión
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
        }
    }
    
    
    void borrardescuento() {
        int row = informacion.getSelectedRow();
        try {
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "No hay una fila seleccionada");
            }
            else {
                String query = "delete from descuentos where id=" + idc;
                
                connection = conexion.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "descuentos eliminado exitosamente");
                limpiartabla();
            }
        } catch (Exception e){
            limpiartabla();
        }
        finally {
            //Vamos a limpiar la memoria destinada para la consulta
            if(pst != null)
            {
                try
                {
                    pst.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
            //Vamos a cerrar la conexión
            if(connection != null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException error)
                {
                    error.printStackTrace();
                }
            }
        }
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jpanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        informacion = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        codigodescuento = new javax.swing.JTextField();
        porcentajedescu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        informacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo descuento", "porcentaje descuento"
            }
        ));
        informacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                informacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(informacion);

        agregar.setText("agregar producto");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        borrar.setText("borrar producto");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        editar.setText("editar producto");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        salir.setText("salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        porcentajedescu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajedescuActionPerformed(evt);
            }
        });

        jLabel2.setText("codigo de descuento");

        jLabel3.setText("porcentaje de descuento");

        jButton1.setText("test coneccion ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        limpiar.setText("limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jLabel4.setText("los porcentajes se agregan");

        jLabel5.setText("sin el %");

        javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
        jpanel2.setLayout(jpanel2Layout);
        jpanel2Layout.setHorizontalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanel2Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(codigodescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpanel2Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(jpanel2Layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jpanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3))
                                .addGroup(jpanel2Layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addComponent(porcentajedescu, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jpanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel4))
                                .addGroup(jpanel2Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(jLabel5))))
                        .addGroup(jpanel2Layout.createSequentialGroup()
                            .addComponent(editar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(borrar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(agregar)
                            .addGap(4, 4, 4)))
                    .addComponent(salir))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpanel2Layout.setVerticalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addComponent(limpiar)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigodescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porcentajedescu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editar)
                    .addComponent(agregar)
                    .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(salir)
                        .addComponent(borrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setText("gestion de descuentos ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(connection == null){
            JOptionPane.showMessageDialog(null, "Connection error");
        }
        else {
            JOptionPane.showMessageDialog(null, "Connection was successful");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void porcentajedescuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajedescuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajedescuActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        main.mostrarMenu();
    }//GEN-LAST:event_salirActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        editardescuento();
        retrieve();
        limpiar();
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        // TODO add your handling code here:
        borrardescuento();
        retrieve();
        limpiar();
    }//GEN-LAST:event_borrarActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
        agregardescuento();
        retrieve();
        limpiar();
    }//GEN-LAST:event_agregarActionPerformed

    private void informacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informacionMouseClicked
        // TODO add your handling code here:

        int row = informacion.getSelectedRow();

        if (row < 0){
            JOptionPane.showMessageDialog(null, "No hay una fila seleccionada");
        }
        else {
            String codigo = (String)informacion.getValueAt(row, 0);
            String porcentaje = (String)informacion.getValueAt(row, 1);

            codigodescuento.setText(codigo);
            porcentajedescu.setText(porcentaje);
            
        }
    }//GEN-LAST:event_informacionMouseClicked

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
            java.util.logging.Logger.getLogger(descuentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(descuentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(descuentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(descuentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new descuentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField codigodescuento;
    private javax.swing.JButton editar;
    private javax.swing.JTable informacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField porcentajedescu;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
