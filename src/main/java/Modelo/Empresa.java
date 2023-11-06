/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDAO.EmpresaDAO;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebav
 */
public class Empresa
{

    
    private List<Bus> flota;
    private String id;
    private String RUC;
    private String nombre;
    private String razonSocial;
    private Pasajero titular;
    private EmpresaDAO dao = new EmpresaDAO();
    private Date fechaRegistro;
    private boolean estado;

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Empresa(List<Bus> flota, String nombre, String razonSocial, Pasajero titular, String id, String RUC, Date fecharRegistro) {
        this.flota = new ArrayList<>();
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.titular = titular;
        this.id = id;
        this.RUC = RUC;
        this.fechaRegistro = fecharRegistro;
        this.estado = true;
    }
    
   
    public Empresa(String id) 
        {
            Empresa em = recuperarDeBD(id);
            this.id = em.getId();
            this.nombre = em.getNombre();
            this.razonSocial = em.getRazonSocial();
            this.titular = em.getTitular();
            this.flota = em.getFlota();
            this.RUC = em.getRUC();
            this.fechaRegistro = em.getFechaRegistro();
             this.estado = true;
        }
    public Empresa() 
    {
        this.flota = new ArrayList<>();
         this.estado = true;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Bus> getFlota() {
        return flota;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public void setFlota(List<Bus> flota) {
        this.flota = flota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Pasajero getTitular() {
        return titular;
    }

    public void setTitular(Pasajero titular) {
        this.titular = titular;
    }

    public EmpresaDAO getDao() {
        return dao;
    }

    public void setDao(EmpresaDAO dao) {
        this.dao = dao;
    }
    
    private Empresa recuperarDeBD(String id)
    {
        return (Empresa)dao.read(id);
    }
    

   
    
    
    
}
