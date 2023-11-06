/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDAO.TarjetaDAO;
import java.util.Date;

/**
 *
 * @author sebav
 */
public class Tarjeta 
{
    private double saldo;
    private TarjetaDAO dao = new TarjetaDAO();
    private String id;
    private String idDueño;
    private Date fecha_compra;

    public Tarjeta(double saldo, String id, String nroDoc, Date fecha_compra) {
        this.saldo = saldo;
        this.id = id;
        this.idDueño = nroDoc;
        this.fecha_compra = fecha_compra;
    }

    

    public  Tarjeta() {
         this.saldo = 0.00;
         this.id = "";
        this.idDueño = "";
        this.fecha_compra=null;
        
         
    }
    
     public Tarjeta(String id) {
         this.saldo = 0.00;
         this.idDueño = id;
         this.id = generarId();
         this.fecha_compra = new Date();
         System.out.println("nueva tarjeta : " + this.id) ;
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

    public String getIdDueño() {
        return idDueño;
    }

    public void setIdDueño(String idDueño) {
        this.idDueño = idDueño;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }
    
    private String generarId()
    {
        return dao.recuperarPk("ID_TARJETA", "tarjeta", "TR-");
    }

    public boolean guardarEnBD()
    {
        dao.create(this);
        return true;
       
    }
    public boolean consumir( String idRuta,String idBus)
    {
      System.out.println(idBus);
      Bus bus = new Bus(idBus);
      Double tarifa = bus.getRuta().getTarifa();
      this.saldo -= tarifa;
     
      if( dao.update(this)!=false)
      {
          dao.registrarConsumo(this, bus);
      }
      else
      {
          return false;
      }
      return true;
        
    }
    
    
    public boolean recargar(Double monto, String tipoPago)
    {
      this.saldo += monto;
      if( dao.update(this)!=false)
      {
          dao.registrarRecarga(this, monto, tipoPago);
      }
      return false;
        
    }

    
}
