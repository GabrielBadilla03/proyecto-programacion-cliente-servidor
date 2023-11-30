/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author gabri
 */
public class Inventario extends Producto{
    productos produ = new productos();

    public Inventario(String nombreproducto, double precio, int cantidadDisponible, String tipoproducto, String fechaexpira) {
        super(nombreproducto, precio, cantidadDisponible, tipoproducto, fechaexpira);
    }
    
    public void administrarproductos(){
        JOptionPane.showMessageDialog(null, "en el siguiente apartado se prodra administrar el invetario");
        produ.setVisible(true);
    }
    
}
