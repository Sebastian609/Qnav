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
    private String Nombre;
    private boolean Estado;

    private int nroOrden;
    private double Latitud;
    private double Longitud;

    public Paradero(String Id, String Nombre, boolean Estado,  int nroOrden, double Latitud, double Longitud) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Estado = Estado;
      
        this.nroOrden = nroOrden;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
    }

    
   
   
  
    public Paradero() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(int nroOrden) {
        this.nroOrden = nroOrden;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double Latitud) {
        this.Latitud = Latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double Longitud) {
        this.Longitud = Longitud;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    
    
}
