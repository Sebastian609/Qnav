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
<%@page import="Modelo.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rutas</title>
        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
        <script src="js/mapa.js"></script>
        <script src="js/consumo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <!-- Incluye la librería de jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


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

                            session = request.getSession();
                            Pasajero user = ((Pasajero) session.getAttribute("user"));
                            List<Empresa> empresas = ((List<Empresa>) session.getAttribute("empresas"));
                           List<Tarjeta> Tarjetas = new ArrayList<Tarjeta>();
                           Tarjetas = user.getTarjetasPasajero();


                        %>
                        <%=user.getNombres().toUpperCase() + " "
                                + user.getApellidoPaterno().toUpperCase() + " "
                                + user.getApellidoMaterno().toUpperCase() + " "%>
                    </h2>

                    <p id="pasajeroId"><%=user.getId()%></p>
                    <div class="separador"></div>
                    <div class="tarjetas">
                        <p>Tarjetas</p>   
                        <select name="tarjetaId" id="tarjetaId" class="dato tarjetero" >
                            <% if (Tarjetas != null) { %>
                            <% for (Tarjeta tarjeta : Tarjetas) {%>
                            <option id-saldo="<%=tarjeta.getSaldo()%>" value="<%= tarjeta.getId()%>_<%=tarjeta.getSaldo()%>"><%= tarjeta.getId() + " S/. " + tarjeta.getSaldo()%></option>
                            <% } %>
                            <% } else { %>
                            <option value="">No hay tarjetas disponibles.</option>
                            <% }%>
                        </select>

                    </div>




                </div>

                <input type="text" id="campoBusquedaEmpresa" name="BuscarEmpresa" class="dato-full" placeholder="Buscar Empresa de Transportes">
                <div class="scroll--buses">
                    <% for (Empresa empresa : empresas) { %>
                    <div class="dato-scroll" id="<%= empresa.getRazonSocial().toLowerCase()%>">
                        <%=empresa.getRazonSocial()%>
                        <% for (Bus bus : empresa.getFlota()) { %>
                        <div class="dato-bus" id="busId"  id-data="<%= bus.getRuta().getId()%>_<%= bus.getId()%>_<%=bus.getRuta().getTarifa()%>">
                            <p> Ruta: <%=bus.getRuta().getNombre()%>, Placa: <%=bus.getPlaca()%>, (S/.): <%=bus.getRuta().getTarifa()%></p>
                        </div>
                        <% } %>
                    </div>
                    <% } %>
                </div>


                <button class="button" id="miBoton" >Simular Cobro</button>
            </div>
            <div class="buscar-paradero">
                <input type="search" name="buscar-paradero" class="buscar" placeholder="¿A cuál paradero?">
                <input type="submit" name ="buscar-paradero-boton" value="Buscar" class="button buscar-boton ">
            </div>


        </div>
        <div id='map'></div>
        <script src="js/buscarEmpresa.js"></script> 
    </body>


    <script src="js/simularCobro.js"></script>

</html>
