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
    private String fechaexpira;
    private String tipoproducto;
    private String nombreproducto;
    private double precio;
    private int cantidadDisponible;

    public Producto( String nombreproducto, double precio, int cantidadDisponible, String tipoproducto, String fechaexpira) {
        this.nombreproducto = nombreproducto;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.tipoproducto = tipoproducto;
        this.fechaexpira = fechaexpira;
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
        return "Producto{" + ", nombreproducto=" + nombreproducto + ", precio=" + precio + ", cantidadDisponible=" + cantidadDisponible + '}';
    }
    
}
