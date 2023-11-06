/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Config;

import Modelo.Pasajero;
import Modelo.Tarjeta;

/**
 *
 * @author sebav
 */
public class Consumo {

    
    public static void main(String[] args) 
    {
       Pasajero pasajero = new Pasajero("QUISPE@GMAIL.COM", "123");
       Tarjeta tarjeta = pasajero.recuperarTarjeta("TR-001");
       tarjeta.consumir("RT-001","BS-001");
       System.out.println(tarjeta.getSaldo() + " es el nuevo saldo de la tarjeta " + tarjeta.getId());
    }
    
}
