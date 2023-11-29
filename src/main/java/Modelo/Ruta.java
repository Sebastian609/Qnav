/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDAO.RutaDAO;
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
    private Double Tarifa;
    private String Color;
    private List<Paradero> Paraderos ;
    private RutaDAO dao = new RutaDAO();
    private boolean Estado;

    public RutaDAO getDao() {
        return dao;
    }

    public void setDao(RutaDAO dao) {
        this.dao = dao;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public Ruta(String Id, String Nombre, Double Tarifa, String Color, List<Paradero> Paraderos, boolean Estado) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Tarifa = Tarifa;
        this.Color = Color;
        this.Paraderos = new ArrayList<>();
     
        this.Estado = Estado;
    }
    
    public Ruta(String id)
    {
        System.out.print(id+"___666666666666666");
        Ruta obj = recuperarDeBD(id);
        this.Id = obj.getId();
        this.Nombre = obj.getNombre();
        this.Tarifa = obj.getTarifa();
        this.Color = obj.getColor();
        this.Paraderos = obj.getParaderos();
    }

    public Ruta() {
        this.Paraderos = new ArrayList<>();
        
    
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

    public Double getTarifa() {
        return Tarifa;
    }

    public void setTarifa(Double Tarifa) {
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
    
    private Ruta recuperarDeBD(String id)
    {
        return  (Ruta)dao.read(id);
    }
    
    
    
}
