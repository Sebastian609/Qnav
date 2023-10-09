<%-- 
    Document   : ruta
    Created on : 3 oct. 2023, 16:59:55
    Author     : sebav
--%>

<%@page import="Modelo.Bus"%>
<%@page import="Modelo.Ruta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Tarjeta"%>
<%@page import="Modelo.Pasajero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rutas</title>
        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
      <script src="js/mapa.js"></script>
      <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />


        <link rel="stylesheet" href="style/normalize.css">
        <link rel="stylesheet" href="style/style.css">
        <link rel="stylesheet" href="style/cobro.css">





    </head>

    <body class="short">
        <header class="hero"> <!--hay que delimitar cada parte de la página-->
            <!--vamos a dividir "hero" diferentes contenedores-->
            <nav class="nav__hero"> <!--barra de navgacion en "hero"-->
                <div class="container nav__container"> <!--contenedor dentro del navegador-->
                    <div class="logo">
                        <h2 class="logo__name">QueryNav<span class="point">.</span></h2>
                    </div>
                    <div class="links">
                        <a href="comprar.jsp" class="link">Comprar Tarjeta</a>
                        <a href="recarga.jsp" class="link">Recarga</a>
                        <a href="index.jsp" class="link--active">Cerrar Sesión</a>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container-ruta">


            <div class="info-card">
                <div class="data-user">
                    <h2 class="card__title--center">

                        <%

                            //session = request.getSession();
                            Pasajero pas = ((Pasajero) request.getAttribute("miObjeto"));
                            List<Ruta> rutas = ((List<Ruta>) request.getAttribute("Rutas"));
                            List<Tarjeta> Tarjetas = new ArrayList<Tarjeta>();

                            if (pas == null) {
                                pas = (Pasajero) session.getAttribute("objetoSesion");
                            }
                            Tarjetas = pas.getTarjetasPasajero();

                            session.setAttribute("objetoSesion", pas);


                        %>
                        <%=pas.getNombres().toUpperCase() + " "
                                + pas.getApellidoPaterno().toUpperCase() + " "
                                + pas.getApellidoMaterno().toUpperCase() + " "
                                + Tarjetas.size()%>
                    </h2>
                    <div class="separador"></div>
                    <form action="ControladorConsumo">
                        <select name="tarjetaId" class="dato">
                            <% if (Tarjetas != null) { %>
                            <% for (Tarjeta tarjeta : Tarjetas) {%>
                            <option value="<%= tarjeta.getId()%>"><%= "Tarjeta " + tarjeta.getId() + " S/. " + tarjeta.getSaldo()%></option>
                            <% } %>
                            <% } else { %>
                            <option value="">No hay tarjetas disponibles.</option>
                            <% }%>
                        </select>
                        <select name="rutaId" class="dato">
                            <% if (rutas != null) { %>
                            <% for (Ruta ruta : rutas) {
                                    List<Bus> buses = ruta.getFlota();
                                    for (Bus bus : buses) {%>
                            <option value="<%= ruta.getId()%>_<%= bus.getPlaca()%>"><%= "Ruta " + ruta.getId() + " S/. " + ruta.getTarifa() + " Bus : " + bus.getPlaca()%></option>
                            <% } %>
                            <% } %>
                            <% } else { %>
                            <option value="">No hay rutas disponibles.</option>
                            <% }%>
                        </select>
                        <button class="button" >Simular Cobro</button>
                    </form>



                    <div class="card__title"><span  id="saldor-tarjeta" class="point"></span></div>
                </div>
                <div class="card__title"><span  id="saldor-tarjeta" class="point">Rutas</span></div>

                <div class="scroll--buses">
                    <div class="bus--nombre">

                        <p>Linea Polanco</p>
                    </div>


                </div>

            </div>
            <div class="buscar-paradero">
                <input type="search" name="buscar-paradero" class="buscar" placeholder="¿A cuál paradero?">
                <input type="submit" name ="buscar-paradero-boton" value="Buscar" class="button buscar-boton ">
            </div>
            
           
        </div>
        <div id='map'></div>

    </body>
     
  
    <script src="js/simularCobro.js"></script>

</html>
