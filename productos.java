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

/**
 *
 * @author dilmergo
 */
public class productos extends javax.swing.JFrame {
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
    public productos() {
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
        idprodu.setText(""+idc);
        nombreprodu.setText("");
        precioprodu.setText("");
        fechaexpi.setText("");
        tipoprodu.setText("");
    }
    
    void retrieve(){
        String query = "select * from informacion";
        
        try {
            connection = conn.getConexion();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            st = connection.createStatement();
            Object[] estudiante = new Object[5];
            model = (DefaultTableModel) informacion.getModel();
            while (rs.next()) {
                estudiante[0] = rs.getInt("id");
                estudiante[1] = rs.getString("nombrep");
                estudiante[2] = rs.getString("precio");
                estudiante[3] = rs.getString("tipo");
                estudiante[4] = rs.getString("fecha");
                
                
                model.addRow(estudiante);
            }
            informacion.setModel(model);
        } catch (Exception e){
            System.out.println("error en la obtencion de datos de la tabla: "+ e.getMessage());
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
    
    void agregarproducto(){     
        String nombre = nombreprodu.getText();
        String precio = precioprodu.getText();
        String tipo = tipoprodu.getText();
        String fechaexpira = fechaexpi.getText();
       
        try {
            if(nombre.equals("")||
               precio.equals("")||
               tipo.equals("")||
               fechaexpira.equals("")){
               JOptionPane.showMessageDialog(null, "hay espacios vacios");
               limpiartabla();
            }else{
                String query = "insert into estudiante("+"nombre,precio,tipo,fechaexpiracion)"+"values('"+nombre+"','"+precio+"','"+tipo+"','"+fechaexpira+"')";
                connection = conn.getConexion();
                pst = connection.prepareStatement(query);
                st = connection.createStatement();
                pst.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "producto agregado exitosamente");
                limpiartabla();
            }
            }
        catch (Exception e){
            System.out.println("error al agregar el prodcuto: "+ e.getMessage());
            limpiartabla();
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
    
    void editarproducto() {
        String nombre = nombreprodu.getText();
        String precio = precioprodu.getText();
        String tipo = tipoprodu.getText();
        String fechaexpira = fechaexpi.getText();
       
        try {
            if(nombre.equals("")||
               precio.equals("")||
               tipo.equals("")||
               fechaexpira.equals("")){
               JOptionPane.showMessageDialog(null, "hay espacios vacios");
               limpiartabla();
            }
            else {
                String query = "update estudiante set nombrep='"+ nombre + "', precio='" + precio + "', tipo='" + tipo + "', fechaexpira='" + fechaexpira + "' where id=" + idc;
                connection = conn.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "el producto fue modificado");
                limpiartabla();
            }
        } catch (Exception e){
            System.out.println("Error while adding data: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar estudiante");
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
    
    
    void borrarproducto() {
        int row = informacion.getSelectedRow();
        try {
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "No hay una fila seleccionada");
            }
            else {
                String query = "delete from estudiante where id=" + idc;
                
                connection = conn.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente");
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
        jpanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        informacion = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        idprodu = new javax.swing.JTextField();
        nombreprodu = new javax.swing.JTextField();
        precioprodu = new javax.swing.JTextField();
        tipoprodu = new javax.swing.JTextField();
        fechaexpi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        informacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "nombre", "precio", "tipo", "fecha expiracion"
            }
        ));
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

        idprodu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idproduActionPerformed(evt);
            }
        });

        precioprodu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioproduActionPerformed(evt);
            }
        });

        fechaexpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaexpiActionPerformed(evt);
            }
        });

        jLabel1.setText(" id del producto");

        jLabel2.setText("nombre del producto");

        jLabel3.setText("precio del producto");

        jLabel4.setText("tipo del producto");

        jLabel5.setText("fecha expiracion");

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

        javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
        jpanel2.setLayout(jpanel2Layout);
        jpanel2Layout.setHorizontalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addComponent(salir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregar)))
                .addGap(18, 18, 18)
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(precioprodu)
                            .addComponent(tipoprodu)
                            .addComponent(nombreprodu, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(idprodu)
                            .addComponent(fechaexpi)))
                    .addComponent(jButton1)
                    .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jpanel2Layout.setVerticalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel2Layout.createSequentialGroup()
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addComponent(limpiar)
                        .addGap(16, 16, 16)
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idprodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(24, 24, 24)
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreprodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24)
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(precioprodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipoprodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaexpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(borrar)
                    .addComponent(editar)
                    .addComponent(salir)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setText("gestion de invetario ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(219, 219, 219))
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

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_salirActionPerformed

    private void fechaexpiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaexpiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaexpiActionPerformed

    private void precioproduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioproduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioproduActionPerformed

    private void idproduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idproduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idproduActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
        agregarproducto();
    }//GEN-LAST:event_agregarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(connection == null){
            JOptionPane.showMessageDialog(null, "Connection error");
        }
        else {
            JOptionPane.showMessageDialog(null, "Connection was successful");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        editarproducto();
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        // TODO add your handling code here:
        borrarproducto();
    }//GEN-LAST:event_borrarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

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
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JButton editar;
    private javax.swing.JTextField fechaexpi;
    private javax.swing.JTextField idprodu;
    private javax.swing.JTable informacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField nombreprodu;
    private javax.swing.JTextField precioprodu;
    private javax.swing.JButton salir;
    private javax.swing.JTextField tipoprodu;
    // End of variables declaration//GEN-END:variables
}
