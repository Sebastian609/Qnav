/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Config;

import Modelo.Bus;
import Modelo.Empresa;
import Modelo.Paradero;
import Modelo.Pasajero;
import Modelo.Persona;
import Modelo.Transportista;
import ModeloDAO.EmpresaDAO;
import java.util.List;
import javax.xml.soap.SOAPFault;

/**
 *
 * @author sebav
 */
public class inicioSesion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EmpresaDAO dao = new EmpresaDAO();
        List<Empresa> empresas = dao.obtenerTodos();
        System.out.println("empresas recuperadas: " + empresas.size());
        for (Empresa em : empresas) {
            System.out.println("Empresa " + em.getRazonSocial());
            List<Bus> buses = em.getFlota();
            for (Bus bus : buses) 
            {
                System.out.println("bus : " + bus.getPlaca() + " " + bus.getTransportista().getNombres());
                List<Paradero> paraderos = bus.getRuta().getParaderos();
                System.out.println("Ruta: " + bus.getRuta().getNombre());
                for (Paradero paradero : paraderos) 
                {
                    System.out.println("Paradero : " + paradero.getNombre());
                }
            }
        }

    }

}
