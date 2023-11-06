/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Config;

import Modelo.Pasajero;

/**
 *
 * @author sebav
 */
public class compraTarjeta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pasajero pas = new Pasajero("QUISPE@GMAIL.COM","123");
        pas.comprarTarjeta(pas.getId());
       
        
    }
    
}
