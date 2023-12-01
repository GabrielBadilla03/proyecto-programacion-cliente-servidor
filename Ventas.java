/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;

import conection.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabri
 */
public class Ventas extends Cajas{
    descuentos descu = new descuentos();
    carrito Carrito = new carrito();
    ArrayList<descuentos> des = descu.descuen;
    ArrayList<Inventario> carrito2 = Carrito.carrito;
    
    ResultSet rs;
    int idc = 0;
    PreparedStatement pst;
    Conection conexion = new Conection(); 	
    Connection connection = conexion.getConexion();
    DefaultTableModel model;
    Statement st;

    public Ventas(int asignarcaja, Date horaEntrada, Date horaSalida, String nombre, int cedula, int telefono, String correo) {
        super(asignarcaja, horaEntrada, horaSalida, nombre, cedula, telefono, correo);
    }
    
    public void copiarcarrito(){
        for(int i = 0;i<Carrito.carrito.size();i++){
            carrito2.add(Carrito.carrito.get(i));
        }
        for(int i = 0;i<descu.descuen.size();i++){
            des.add(descu.descuen.get(i));
        }
    }
    
    
    void retrieve(){
        String query = "select * from inventario";
        String query2 = "select * from ventas";
        
        try {
            connection = conexion.getConexion();
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery(query);
            Object[] inventario = new Object[6];
            while (rs.next()) {
                inventario[0] = rs.getInt("id");
                inventario[1] = rs.getString("nombrep");
                inventario[2] = rs.getString("precio");
                inventario[3] = rs.getString("tipo");
                inventario[4] = rs.getInt("cantidad");
                inventario[5] = rs.getString("fechaexpira");
                
                for(int i = 0;i<carrito2.size();i++){
                    if(inventario[0].equals(carrito2.get(i).getId())){
                        int id = carrito2.get(i).getId();
                        String nombre = carrito2.get(i).getNombreproducto();
                        double precio = carrito2.get(i).getPrecio();
                        String tipo = carrito2.get(i).getTipoproducto();
                        int cant = rs.getInt("cantidad");
                        int cantidad = cant - carrito2.get(i).getCantidadDisponible();
                        String fechaexpira = carrito2.get(i).getFechaexpira();
                        
                        String quer = "update inventario set nombrep='"+ nombre + "', precio='" + precio + "', tipo='" + tipo + "', cantidad='" + cantidad + "', fechaexpira='" + fechaexpira + "' where id=" + idc;               
                        
                        String quer2 = "insert intoventas(id, nombrep, precio, tipo, cantidad, fechaexpira)" + "values ('"+ nombre + "', '" + precio + "', '" + tipo + "', '" + cantidad + "', '" + fechaexpira + "')";
                        
                        
                        connection = conexion.getConexion();
                        pst = connection.prepareStatement(quer);
                        pst.executeUpdate(quer);
                        
                        connection = conexion.getConexion();
                        pst = connection.prepareStatement(quer2);
                        pst.executeUpdate(quer2);
                       
                    }
                }
                
                JOptionPane.showMessageDialog(null, inventario[0]);
                
            }
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
    
    
    public void comprar(){
        JOptionPane.showMessageDialog(null, "en el siguiente apartado podra hacer el carrito de compra");
        Carrito.setVisible(true);
    }
    
    public void vender(){
        copiarcarrito();
        JOptionPane.showMessageDialog(null,"tu carrito es: " + carrito2);
        int opcion = Integer.parseInt(JOptionPane.showInputDialog("quiere editar el carrito?\n1-si\n2-no"));
        if(opcion == 1){
            int produ = Integer.parseInt(JOptionPane.showInputDialog(carrito2.toString()+"\ningrese el id del producto que desea eliminar del carrito"));
            for(int i = 0;i<carrito2.size();i++){
                if(produ == carrito2.get(i).getId()){
                    carrito2.remove(i);
                    JOptionPane.showMessageDialog(null, "producto eliminado\n"+carrito2);
                    return;
                }
            } 
        }
        int opci = Integer.parseInt(JOptionPane.showInputDialog("desea ingresar un codigo de descuento?\n1-si\n2-no"));
        if(opci == 1){
            int descuento = Integer.parseInt(JOptionPane.showInputDialog("lista de descuentos\ningrese el id del que quiere usar\n"+des));
            int descuentofinal = 0;
            for(int i = 0;i<des.size();i++){
                if(descuento == des.get(i).getIdDescu())
                    descuentofinal = des.get(i).getPorceDescu();
            }
            double preciofi = 0;
            double preciofinal = preciofi - (preciofi * descuentofinal);
            for(int i = 0;i<carrito2.size();i++){
                preciofi += carrito2.get(i).getPrecio()*carrito2.get(i).getCantidadDisponible();
            }
            int comprar = Integer.parseInt(JOptionPane.showInputDialog("el precio final de tu carrito es:"+preciofinal
                                                                        +"\nrealizar compra?\n1-si\n2-no"));
            if(comprar == 1){
                retrieve();
                JOptionPane.showMessageDialog(null, "su compra se a realizado con exito");
                for(int i = 0;i<carrito2.size();i++){
                    carrito2.remove(i);
                }
                for(int i = 0;i<Carrito.carrito.size();i++){
                    Carrito.carrito.remove(i);
                }
            }else{
                JOptionPane.showMessageDialog(null, "se a cancelado la compra");
            }
            
        }
        else{
            double preciofinal = 0;
            for(int i = 0;i<carrito2.size();i++){
                preciofinal += carrito2.get(i).getPrecio()*carrito2.get(i).getCantidadDisponible();
            }
            int comprar = Integer.parseInt(JOptionPane.showInputDialog("el precio final de tu carrito es:"+preciofinal
                                                                        +"\nrealizar compra?\n1-si\n2-no"));
            if(comprar == 1){
                retrieve();
                JOptionPane.showMessageDialog(null, "su compra se a realizado con exito");
                for(int i = 0;i<carrito2.size();i++){
                    carrito2.remove(i);
                }
                for(int i = 0;i<Carrito.carrito.size();i++){
                    Carrito.carrito.remove(i);
                }
            }else{
                JOptionPane.showMessageDialog(null, "se a cancelado la compra");
            }    
        }    
    } 
}
