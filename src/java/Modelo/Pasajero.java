/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebav*/
public class Pasajero extends Persona {
    private List<Tarjeta> tarjetasPasajero;


    public Pasajero(List<Tarjeta> tarjetasPasajero, Tarjeta tarjetaEnUso, String nroDoc, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Date fechaRegistro, String correo, String contraseña, int telefono) {
        super(nroDoc, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro, correo, contraseña, telefono);
        this.tarjetasPasajero = new ArrayList<Tarjeta>();;
        
    }

    public Pasajero(List<Tarjeta> tarjetasPasajero, Tarjeta tarjetaEnUso) {
        this.tarjetasPasajero = new ArrayList<Tarjeta>();;
        
    }
    
    

    public Pasajero() {
        this.tarjetasPasajero = new ArrayList<Tarjeta>();
    }

    public List<Tarjeta> getTarjetasPasajero() {
        return tarjetasPasajero;
    }

    public void setTarjetasPasajero(List<Tarjeta> tarjetasPasajero) {
        this.tarjetasPasajero = tarjetasPasajero;
    }

 
    // Getters y setters para los atributos heredados de la clase Persona

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
