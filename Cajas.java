/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Cajas extends Empleados{
    empleado empleado = new empleado();
    descuentos descuentos = new descuentos();

    public Cajas(int asignarcaja, Date horaEntrada, Date horaSalida, String nombre, int cedula, int telefono, String correo) {
        super(asignarcaja, horaEntrada, horaSalida, nombre, cedula, telefono, correo);
    }
    
    public void administrarempleados(){
        JOptionPane.showMessageDialog(null, "en el siguiente apartado se podra administrar los empleados");
        empleado.setVisible(true);  
    }
    
    public void adminsitrardescuentos(){
        JOptionPane.showMessageDialog(null, "en este apartado se podran administrar los descuentos");
      
    }
}
