/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoclienteservidor;

import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Main {
    public static void main(String[] args){
        // Solicitar contraseña
        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");

        // Verificar la contraseña
        if (verificarContraseña(password)) {
            // Contraseña correcta, mostrar menú
            mostrarMenu();
        } else {
            // Contraseña incorrecta, mostrar mensaje y salir
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Saliendo del programa.");
        }
    }

    // Método para verificar la contraseña
    private static boolean verificarContraseña(String password) {
        // Contraseña de ejemplo, puedes cambiarla según tus necesidades
        return password != null && password.equals("admin");
    }

    // Método para mostrar el menú
    private static void mostrarMenu() {
        Cajas cajas = new Cajas(0,null,null,null,0,0,null);
        Inventario inventario = new Inventario(null,0,0,null,null);
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menú Principal\n" +
                            "1. Administrar Empleados\n" +
                            "2. Administrar Descuentos\n" +
                            "3. Administrar Productos\n" +
                            "4. Salir\n" +
                            "Ingrese la opción deseada:"));

            switch (opcion) {
                case 1:
                    cajas.administrarempleados();
                    break;
                case 2:
                    cajas.adminsitrardescuentos();
                    break;
                case 3:
                    inventario.administrarproductos();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }
}
 