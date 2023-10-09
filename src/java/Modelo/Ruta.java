/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebav
 */
public class Ruta 
{
    private String Id;
    private String Nombre;
    private String Tarifa;
    private String Color;
    private List<Paradero> Paraderos ;
    private List<Bus> flota;

    public Ruta(String Id, String Nombre, String Tarifa, String Color, List<Paradero> Paraderos) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Tarifa = Tarifa;
        this.Color = Color;
        this.Paraderos = new ArrayList<>();
        this.flota =  new ArrayList<>();
    }

    public Ruta() {
        this.Paraderos = new ArrayList<>();
        this.flota =  new ArrayList<>();
    
    }

    public List<Bus> getFlota() {
        return flota;
    }

    public void setFlota(List<Bus> flota) {
        this.flota = flota;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTarifa() {
        return Tarifa;
    }

    public void setTarifa(String Tarifa) {
        this.Tarifa = Tarifa;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public List<Paradero> getParaderos() {
        return Paraderos;
    }

    public void setParaderos(List<Paradero> Paraderos) {
        this.Paraderos = Paraderos;
    }
    
    
    
}
