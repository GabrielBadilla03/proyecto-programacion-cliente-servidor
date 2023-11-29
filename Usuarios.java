/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;

/**
 *
 * @author gabri
 */
public class Usuarios {
    
    private String nombre;
    private int cedula;
    private int telefono;
    private int correo;

    public Usuarios(String nombre, int cedula, int telefono, int correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCorreo() {
        return correo;
    }

    public void setCorreo(int correo) {
        this.correo = correo;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", cedula=" + cedula + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
    
}
