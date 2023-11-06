package Config;

import Modelo.Pasajero;
import Modelo.Persona;
import ModeloDAO.PasajeroDAO;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class crearUsuario {

    private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LONGITUD_CODIGO = 6;
    private static final PasajeroDAO dao = new PasajeroDAO();

    public static String generarCodigoAleatorio() {
        SecureRandom aleatorio = new SecureRandom();
        StringBuilder codigoGenerado = new StringBuilder(LONGITUD_CODIGO);

        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            int indice = aleatorio.nextInt(CARACTERES_PERMITIDOS.length());
            codigoGenerado.append(CARACTERES_PERMITIDOS.charAt(indice));
        }

        return codigoGenerado.toString();
    }

    public static boolean validarCodigo(String codigoGenerado, String codigoIngresado) {
        return codigoGenerado.equals(codigoIngresado);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String correo = "AGUILARTORRES@GMAIL.COM";
        String nroDoc = "12245678";
        String nombres = "ROBERTO";
        String contraseña = "123";
        int telefono = 123456789;
        String apellidoPaterno = "AGUILAR";
        String apellidoMaterno = "TORRES";
        String fechaNacimiento = "1970-04-25";

        if (dao.verificarCorreoExistente(correo,nroDoc  ) == false) {
            System.out.println("INSERTA EL CÓDIGO ENVIADO A TU CORREO");
            String codigoGenerado = generarCodigoAleatorio();
            System.out.println("Código generado: " + codigoGenerado);

            String codigoIngresado = sc.next();

            if (validarCodigo(codigoGenerado, codigoIngresado)) 
            {
                Date fechaActual = new Date();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String fechaFormateada = formatoFecha.format(fechaActual);
                System.out.println("Fecha y hora actual: " + fechaFormateada);

                Date fechaNacimientoParsed = null;
                try {
                    fechaNacimientoParsed = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
                    System.out.println("Fecha de nacimiento: " + new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimientoParsed));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Pasajero pasajero = new Pasajero(nroDoc, nombres, apellidoPaterno, apellidoMaterno, fechaNacimientoParsed, fechaActual, correo, contraseña, telefono);
                System.out.println(pasajero.getId());
                pasajero.guardarEnBD();
                
            } else {
                System.out.println("Código incorrecto");
            }
        }
        else
        {
            System.out.println("Ya existe un correo con esta cuenta");
        }

    }
}
