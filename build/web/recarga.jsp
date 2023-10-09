<%-- 
    Document   : recarga
    Created on : 5 oct. 2023, 22:39:40
    Author     : sebav
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Tarjeta"%>
<%@page import="Modelo.Pasajero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Página de Cobro</title>

        <link rel="stylesheet" href="style/normalize.css">
        <link rel="stylesheet" href="style/style.css">
        
        <style>
        /* Estilos para el formulario */
        #formulario-pago {
           
            margin: 0 auto;
            
               
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"] {
            width: inherit;
            display: block;
            width: 100%;
            padding: 10px;
            padding-left: inherit;
           padding-right: inherit;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 16px;
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            
            
            border: none;
            border-radius: 3px;
            font-size: 18px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
    </head>
    <body>
        <%
            // Recuperar el objeto del alcance de sesión
            //List<Tarjeta> Tarjetas = (List<Tarjeta>) session.getAttribute("listaTarjetas");
            Pasajero pas = (Pasajero) session.getAttribute("objetoSesion");
            List<Tarjeta> Tarjetas = pas.getTarjetasPasajero();
           
          
        %>
        <div class="img"></div>
        <header class="hero"> <!--hay que delimitar cada parte de la página-->
            <!--vamos a dividir "hero" diferentes contenedores-->
            <nav class="nav__hero"> <!--barra de navgacion en "hero"-->
                <div class="container nav__container"> <!--contenedor dentro del navegador-->
                    <div class="logo">
                        <h2 class="logo__name">QueryNav<span class="point">.</span></h2>
                    </div>
                    <div class="links">
     
                        <a href="Verificador?correo=<%= pas.getCorreo()%>&contrasena=<%= pas.getContraseña()%>&tipo_usuario=pasajero">Rutas</a>

                        <a href="index" class="link--active">Cerrar Sesión</a>
                    </div>
                </div>
            </nav>
        </header>

        <section class="services">
            <div class="container-cobro">
                <img src="img/card.png" class="credit-card">



                <form action="ControlardorRecargas" >
                    <input type="hidden" name="tipoPago" value="TP0002"> <!-- Tipo de pago fijo -->

                    <select name="tarjetaId">
                        <% if (Tarjetas != null) { %>
                        <% for (Tarjeta tarjeta : Tarjetas) {%>
                        <option value="<%= tarjeta.getId()%>"><%= tarjeta.getId()%></option>
                        <% } %>
                        <% } else { %>
                        <option value="">No hay tarjetas disponibles.</option>
                        <% }%>
                    </select>
                    

                    <input type="text" name="saldo" placeholder="Saldo a recargar" required>

                    <!-- Agregar otros campos de formulario si es necesario -->
                      <input placeholder="Número de Tarjeta" type="text" id="numero-tarjeta" required>


                    <input placeholder="Fecha de Vencimiento (MM/YY)" type="text" id="fecha-vencimiento" required>


                    <input  placeholder="CVV" type="text" id="cvc" required>
                    <button href="ControlardorRecargas" type="submit">Pagar</button>
                </form>

            </div>
        </section>
    </body>
</html>

