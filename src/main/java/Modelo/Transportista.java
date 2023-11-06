/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDAO.PasajeroDAO;
import ModeloDAO.TarjetaDAO;
import ModeloDAO.TransportistaDAO;
import java.util.Date;

/**
 *
 * @author sebav
 */
public class Transportista extends Persona
{
    
    private String licencia;
    private TransportistaDAO dao = new TransportistaDAO();
    private String nombreUsuario;
   
  

    public Transportista(String id, String licencia, Bus bus, String nroDoc, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Date fechaRegistro, String correo, String contraseña, int telefono,String nombreUsuario) {
        super(id,nroDoc, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro, correo, contraseña, telefono);
        this.licencia = licencia;
        this.nombreUsuario = nombreUsuario;
    }
    
     public Transportista(String id) 
    {
        Transportista ts = (Transportista) dao.read(id);
        if (ts != null) 
        {
            this.id = ts.getId();
            this.nroDoc = ts.getNroDoc();
            this.nombreUsuario = ts.getNombreUsuario();
            this.nombres = ts.getNombres();
            this.apellidoPaterno = ts.getApellidoPaterno();
            this.apellidoMaterno = ts.getApellidoMaterno();
            this.licencia = ts.getLicencia();
            this.contraseña = ts.getContraseña();
            this.telefono = ts.getTelefono();
            this.fechaRegistro = ts.getFechaRegistro();
            this.fechaNacimiento = ts.getFechaNacimiento();
        }
    }

    public Transportista() {
    }
     
    
    
    

    public TransportistaDAO getDao() {
        return dao;
    }

    public void setDao(TransportistaDAO dao) {
        this.dao = dao;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

 
    
   

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    
   
    
}
