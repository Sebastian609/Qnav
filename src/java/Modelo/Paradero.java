/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author sebav
 */
public class Paradero 
{
    private String Id;
    private int nroOrden;
    private double Latitud;
    private double Longitud;

    public Paradero(String Id, int nroOrden, double Latitud, double Longitud) {
        this.Id = Id;
        this.nroOrden = nroOrden;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
    }

    public Paradero() {
    }
    
    
    
}
