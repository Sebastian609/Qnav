
package Config;


import Modelo.Tarjeta;

import Modelo.Pasajero;

/**
 *
 * @author sebav
 */
public class recargarTarjetas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Pasajero pasajero = new Pasajero("QUISPE@GMAIL.COM", "123");
       Tarjeta tarjeta = pasajero.recuperarTarjeta("TR-001");
       tarjeta.recargar(5.50,"TP-004");
       System.out.println(tarjeta.getSaldo() + " es el nuevo saldo de la tarjeta " + tarjeta.getId());
     
    }
    
}
