
package Modelo;

import java.util.Date;

public abstract class Persona {
    
     String nroDoc ;
     String nombres ;
     String apellidoPaterno ;
     String apellidoMaterno ;
     Date fechaNacimiento;
     Date fechaRegistro;
     String correo;
     String contraseña;
     int telefono;


    public Persona(String nroDoc, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Date fechaRegistro, String correo, String contraseña, int telefono) {
        this.nroDoc = nroDoc;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    public Persona() {
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getTelefono() {
        return telefono;
    }

   

    
    

}
