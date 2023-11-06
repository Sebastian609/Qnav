/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDAO.PasajeroDAO;
import ModeloDAO.TarjetaDAO;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebav
 */
public class Pasajero extends Persona {

    private List<Tarjeta> tarjetasPasajero;
    private PasajeroDAO dao;
    private TarjetaDAO daoTarjeta;

    public TarjetaDAO getDaoTarjeta() {
        return daoTarjeta;
    }

    public void setDaoTarjeta(TarjetaDAO daoTarjeta) {
        this.daoTarjeta = daoTarjeta;
    }

    public Pasajero(String id, List<Tarjeta> tarjetasPasajero, Tarjeta tarjetaEnUso, String nroDoc, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Date fechaRegistro, String correo, String contraseña, int telefono) {
        super(id, nroDoc, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro, correo, contraseña, telefono);
        this.tarjetasPasajero = new ArrayList<Tarjeta>();;
        this.dao = new PasajeroDAO();
        this.daoTarjeta = new TarjetaDAO();

    }

    public Pasajero(List<Tarjeta> tarjetasPasajero, Tarjeta tarjetaEnUso) {
        this.tarjetasPasajero = new ArrayList<Tarjeta>();
        this.dao = new PasajeroDAO();
        this.daoTarjeta = new TarjetaDAO();

    }

    //constructor para crear cuenta (con insert en bd)
    public Pasajero(String nroDoc, String nombres, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Date fechaRegistro, String correo, String contraseña, int telefono) {
        super(null, nroDoc, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaRegistro, correo, contraseña, telefono);
        this.tarjetasPasajero = new ArrayList<Tarjeta>();
        this.dao = new PasajeroDAO();
        this.id = dao.recuperarPk("ID_USUARIO", "USUARIO", "US-");
        this.daoTarjeta = new TarjetaDAO();

    }

    public Pasajero(List<Tarjeta> tarjetasPasajero, PasajeroDAO dao) {
        this.tarjetasPasajero = tarjetasPasajero;
        this.dao = new PasajeroDAO();
        this.daoTarjeta = new TarjetaDAO();
    }

    public Pasajero(String correo, String contraseña) 
    {
        this.dao = new PasajeroDAO();
        this.daoTarjeta = new TarjetaDAO();
        Pasajero pass = (Pasajero) dao.inicioSesion(correo, contraseña);
        if (pass != null) {
            this.id = pass.getId();
            this.nroDoc = pass.getNroDoc();
            this.nombres = pass.getNombres();
            this.apellidoPaterno = pass.getApellidoPaterno();
            this.apellidoMaterno = pass.getApellidoMaterno();
            this.correo = pass.getCorreo();
            this.contraseña = pass.getContraseña();
            this.telefono = pass.getTelefono();
            this.fechaRegistro = pass.getFechaRegistro();
            this.fechaNacimiento = pass.getFechaNacimiento();
            this.tarjetasPasajero = pass.getTarjetasPasajero();
        }

    }
    
     public Pasajero(String id) 
    {
        this.dao = new PasajeroDAO();
        this.daoTarjeta = new TarjetaDAO();
        Pasajero pass = (Pasajero) dao.read(id);
        if (pass != null) {
            this.id = pass.getId();
            this.nroDoc = pass.getNroDoc();
            this.nombres = pass.getNombres();
            this.apellidoPaterno = pass.getApellidoPaterno();
            this.apellidoMaterno = pass.getApellidoMaterno();
            this.correo = pass.getCorreo();
            this.contraseña = pass.getContraseña();
            this.telefono = pass.getTelefono();
            this.fechaRegistro = pass.getFechaRegistro();
            this.fechaNacimiento = pass.getFechaNacimiento();
            this.tarjetasPasajero = pass.getTarjetasPasajero();
        }

    }

    public PasajeroDAO getDao() {
        return dao;
    }

    public void setDao(PasajeroDAO dao) {
        this.dao = dao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pasajero() {
        this.tarjetasPasajero = new ArrayList<Tarjeta>();
    }
    //constructor para crear cuenta

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

    //metodos publicos
    public void guardarEnBD() {
        dao.create(this);

    }

    public Tarjeta recuperarTarjeta(String id) {
        for (Tarjeta tarjeta : tarjetasPasajero) {
            if (tarjeta.getId().equals(id)) {
                System.out.println("tarjeta encontrada");
                return tarjeta;

            }
        }
        return null;
    }

    public boolean comprarTarjeta(String id) //añade la tarjeta al pasajero en bd y en el onjetp
    {
        Tarjeta tarjeta = new Tarjeta(id);
        tarjetasPasajero.add(tarjeta);
        tarjeta.guardarEnBD();

        return true;
    }

}
