/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;

import java.util.Date;

/**
 *
 * @author gabri
 */
public class Empleados extends Usuarios{
    private int asignarcaja;
    private static double salarioHora = 8.25;
    private Date horaEntrada;
    private Date horaSalida;

    public Empleados(int asignarcaja, Date horaEntrada, Date horaSalida, String nombre, int cedula, int telefono, String correo) {
        super(nombre, cedula, telefono, correo);
        this.asignarcaja = asignarcaja;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }
    
    public int getAsignarcaja() {
        return asignarcaja;
    }

    public void setAsignarcaja(int asignarcaja) {
        this.asignarcaja = asignarcaja;
    }

    public static double getSalarioHora() {
        return salarioHora;
    }

    public static void setSalarioHora(double salarioHora) {
        Empleados.salarioHora = salarioHora;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    
    
    
    
}
