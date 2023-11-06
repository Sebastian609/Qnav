/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDAO.BusDAO;

/**
 *
 * @author sebav
 */
public class Bus 
{
    private String id;
    private String Placa;
    private int Aforo;
    private Transportista transportista;
    private boolean Estado;
    private int aforo;
    private BusDAO dao = new BusDAO();
    private Ruta ruta;

    public Bus(String id, String Placa, int Aforo, Transportista transportista, boolean Estado, int aforo, Ruta ruta) {
        this.id = id;
        this.Placa = Placa;
        this.Aforo = Aforo;
        this.transportista = transportista;
        this.Estado = Estado;
        this.aforo = aforo;
        this.ruta = ruta;
    }

    public Bus() {
    }

    public Bus(String id) {
        this.id = id;
        Bus bs = (Bus)dao.read(id);
        this.Placa = bs.getPlaca();
        this.Aforo = bs.getAforo();
        this.transportista = bs.getTransportista();
        this.Estado = bs.isEstado();
        this.aforo = bs.getAforo();
        this.ruta = bs.getRuta();
    }
    
    

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public int getAforo() {
        return Aforo;
    }

    public void setAforo(int Aforo) {
        this.Aforo = Aforo;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

   

    public BusDAO getDao() {
        return dao;
    }

    public void setDao(BusDAO dao) {
        this.dao = dao;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    
  
    private Bus recuperarDeBD(String id)
    {
        return (Bus)dao.read(id);
    }
    
}
