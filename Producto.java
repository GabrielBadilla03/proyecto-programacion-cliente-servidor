/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;

/**
 *
 * @author gabri
 */
public class Producto{
    private int id;
    private String fechaexpira;
    private String tipoproducto;
    private String nombreproducto;
    private double precio;
    private int cantidadDisponible;

    public Producto(int id, String nombreproducto, double precio, String tipoproducto, int cantidadDisponible, String fechaexpira) {
        this.nombreproducto = nombreproducto;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.tipoproducto = tipoproducto;
        this.fechaexpira = fechaexpira;
        this.id = id;
    }

    public String getFechaexpira() {
        return fechaexpira;
    }

    public void setFechaexpira(String fechaexpira) {
        this.fechaexpira = fechaexpira;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "id: " + id + ", nombreproducto: " + nombreproducto + ", precio: " + precio + ", tipoproducto: " + tipoproducto + ", cantidadDisponible: " + cantidadDisponible +", fechaexpira: " + fechaexpira +"\n";
    }

   
}
