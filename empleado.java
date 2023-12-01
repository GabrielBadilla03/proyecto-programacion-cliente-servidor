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
public class empleado extends javax.swing.JFrame {
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
    public empleado() {
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
        idemple.setText(""+idc);
        nombreemple.setText("");
        ceduemple.setText("");
        teleemple.setText("");
        emailemple.setText("");
        cajaemple.setText("");
        entradaemple.setText("");
        salidaemple.setText("");
    }
    
    void retrieve(){
        String query = "select * from empleados";
        
        try {
            connection = conexion.getConexion();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            st = connection.createStatement();
            Object[] empleado = new Object[8];
            model = (DefaultTableModel) informacion.getModel();
            while (rs.next()) {
                empleado[0] = rs.getInt("id");
                empleado[1] = rs.getString("nombre");
                empleado[2] = rs.getString("cedula");
                empleado[3] = rs.getString("telefono");
                empleado[4] = rs.getString("email");
                empleado[5] = rs.getString("caja");
                empleado[6] = rs.getString("horaentrada");
                empleado[7] = rs.getString("horasalida");
                
                
                model.addRow(empleado);
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
    
    void agregarempleado(){     
        String nombre = nombreemple.getText();
        String cedula = ceduemple.getText();
        String telefono = teleemple.getText();
        String email = emailemple.getText();
        String caja = cajaemple.getText();
        String entrada = entradaemple.getText();
        String salida = salidaemple.getText();
        
        try {
            if(nombre.equals("")||
               cedula.equals("")||
               telefono.equals("")||
               email.equals("")||
                caja.equals("")|| 
                entrada.equals("")||
                salida.equals("")){
               JOptionPane.showMessageDialog(null, "hay espacios vacios");
               limpiartabla();
            }else{
                String query = "insert into empleados("+"nombre,cedula,telefono,email,caja,horaentrada,horasalida)"+"values('"+nombre+"','"+cedula+"','"+telefono+"','"+email+"','"+caja+"','"+entrada+"','"+salida+"')";
                connection = conexion.getConexion();
                pst = connection.prepareStatement(query);
                st = connection.createStatement();
                pst.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "empleado agregado exitosamente");
                limpiartabla();
            }
            }
        catch (Exception e){
            System.out.println("error al agregar el empleado: "+ e.getMessage());
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
    
    void editarempleado() {
        String nombre = nombreemple.getText();
        String cedula = ceduemple.getText();
        String telefono = teleemple.getText();
        String email = emailemple.getText();
        String caja = cajaemple.getText();
        String entrada = entradaemple.getText();
        String salida = salidaemple.getText();
        
        try {
            if(nombre.equals("")||
               cedula.equals("")||
               telefono.equals("")||
               email.equals("")||
                caja.equals("")|| 
                entrada.equals("")||
                salida.equals("")){
               JOptionPane.showMessageDialog(null, "hay espacios vacios");
               limpiartabla();
            }
            else {
                String query = "update empleados set nombrep='"+ nombre + "', cedula='" + cedula + "', email='" + email + "', caja='" + caja +  "', horaentrada='" +entrada+ "', horasalida='" + salida + "' where id=" + idc;
                connection = conexion.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "el empelado fue modificado");
                limpiartabla();
            }
        } catch (Exception e){
            System.out.println("Error while adding data: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al modificar el empleado");
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
    
    
    void borrarempleado() {
        int row = informacion.getSelectedRow();
        try {
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "No hay una fila seleccionada");
            }
            else {
                String query = "delete from empleados where id=" + idc;
                
                connection = conexion.getConexion();
                pst = connection.prepareStatement(query);
                pst.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "empleado eliminado exitosamente");
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
        jLabel10 = new javax.swing.JLabel();
        limpiar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        informacion = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        idemple = new javax.swing.JTextField();
        nombreemple = new javax.swing.JTextField();
        ceduemple = new javax.swing.JTextField();
        teleemple = new javax.swing.JTextField();
        emailemple = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cajaemple = new javax.swing.JTextField();
        entradaemple = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        salidaemple = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel10.setText("gestion de invetario ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        informacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nombre", "cedula", "telefono", "email", "caja", "horaentrada", "horasalida"
            }
        ));
        informacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                informacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(informacion);

        agregar.setText("agregar empleado");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        borrar.setText("borrar empleado");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        editar.setText("editar empleado");
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

        idemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idempleActionPerformed(evt);
            }
        });

        nombreemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreempleActionPerformed(evt);
            }
        });

        ceduemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceduempleActionPerformed(evt);
            }
        });

        teleemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teleempleActionPerformed(evt);
            }
        });

        emailemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailempleActionPerformed(evt);
            }
        });

        jLabel1.setText(" id del empleado");

        jLabel2.setText("nombre del empleado");

        jLabel3.setText("cedula empleado");

        jLabel4.setText("telefono empleado");

        jLabel5.setText("email empleado");

        jButton1.setText("test coneccion ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("caja empleado");

        cajaemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaempleActionPerformed(evt);
            }
        });

        entradaemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaempleActionPerformed(evt);
            }
        });

        jLabel8.setText("hora entrada");

        jLabel9.setText("hora salida ");

        salidaemple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaempleActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel11.setText("gestion de empleados ");

        jButton3.setText("limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout limpiarLayout = new javax.swing.GroupLayout(limpiar);
        limpiar.setLayout(limpiarLayout);
        limpiarLayout.setHorizontalGroup(
            limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(limpiarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(limpiarLayout.createSequentialGroup()
                        .addComponent(salir)
                        .addGap(64, 64, 64)
                        .addComponent(editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, limpiarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(limpiarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(limpiarLayout.createSequentialGroup()
                                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(limpiarLayout.createSequentialGroup()
                                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ceduemple)
                                            .addComponent(teleemple)
                                            .addComponent(nombreemple, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                            .addComponent(emailemple))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, limpiarLayout.createSequentialGroup()
                                        .addGap(0, 1, Short.MAX_VALUE)
                                        .addComponent(idemple, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(17, Short.MAX_VALUE))
                            .addGroup(limpiarLayout.createSequentialGroup()
                                .addComponent(cajaemple, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(limpiarLayout.createSequentialGroup()
                                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(salidaemple, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(entradaemple, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, limpiarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(93, 93, 93)
                .addComponent(jButton3)
                .addGap(25, 25, 25))
        );
        limpiarLayout.setVerticalGroup(
            limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(limpiarLayout.createSequentialGroup()
                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(limpiarLayout.createSequentialGroup()
                        .addGap(0, 39, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, limpiarLayout.createSequentialGroup()
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nombreemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(ceduemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(teleemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(emailemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cajaemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(entradaemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(salidaemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(limpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(borrar)
                    .addComponent(editar)
                    .addComponent(salir)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        main.mostrarMenu();
 
    }//GEN-LAST:event_salirActionPerformed

    private void emailempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailempleActionPerformed

    private void ceduempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceduempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ceduempleActionPerformed

    private void idempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idempleActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
        agregarempleado();
        retrieve();
        limpiar();
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
        editarempleado();
        retrieve();
        limpiar();
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        // TODO add your handling code here:
        borrarempleado();
        retrieve();
        limpiar();
        
    }//GEN-LAST:event_borrarActionPerformed

    private void nombreempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreempleActionPerformed

    private void teleempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teleempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teleempleActionPerformed

    private void cajaempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaempleActionPerformed

    private void entradaempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entradaempleActionPerformed

    private void salidaempleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaempleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salidaempleActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void informacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informacionMouseClicked
        // TODO add your handling code here:
        int row = informacion.getSelectedRow();
        
        if (row < 0){
            JOptionPane.showMessageDialog(null, "No hay una fila seleccionada");
        }
        else {
            idc = Integer.parseInt((String)informacion.getValueAt(row, 0).toString());
            String nombre = (String)informacion.getValueAt(row, 1);
            String cedula = (String)informacion.getValueAt(row, 2);
            String telefono = (String)informacion.getValueAt(row, 3);
            String email = (String)informacion.getValueAt(row, 4);
            String caja = (String)informacion.getValueAt(row, 5);
            String entrada = (String)informacion.getValueAt(row, 6);
            String salida = (String)informacion.getValueAt(row, 7);
            
            idemple.setText(""+idc);
            nombreemple.setText(nombre);
            ceduemple.setText(cedula);
            teleemple.setText(telefono);
            emailemple.setText(email);
            cajaemple.setText(caja);
            entradaemple.setText(entrada);
            salidaemple.setText(salida);
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
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new empleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField cajaemple;
    private javax.swing.JTextField ceduemple;
    private javax.swing.JButton editar;
    private javax.swing.JTextField emailemple;
    private javax.swing.JTextField entradaemple;
    private javax.swing.JTextField idemple;
    private javax.swing.JTable informacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel limpiar;
    private javax.swing.JTextField nombreemple;
    private javax.swing.JTextField salidaemple;
    private javax.swing.JButton salir;
    private javax.swing.JTextField teleemple;
    // End of variables declaration//GEN-END:variables
}
