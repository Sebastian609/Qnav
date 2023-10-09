/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author sebav
 */
public class Tarjeta 
{
    private double saldo;
    private String id;
    private String nroDoc;
    private Date fecha_compra;

    public Tarjeta(double saldo, String id, String nroDoc, Date fecha_compra) {
        this.saldo = saldo;
        this.id = id;
        this.nroDoc = nroDoc;
        this.fecha_compra = fecha_compra;
    }

    

    public Tarjeta() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    

    
}
