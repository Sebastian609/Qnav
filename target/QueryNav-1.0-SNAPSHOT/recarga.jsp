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
        <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
        <script src="js/recargar.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <link rel="stylesheet" href="style/normalize.css">
        <link rel="stylesheet" href="style/style.css">


    </head>
    <body>
        <%
           
            //List<Tarjeta> Tarjetas = (List<Tarjeta>) session.getAttribute("listaTarjetas");
             session = request.getSession();
            Pasajero pas = (Pasajero) session.getAttribute("user");
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

                        <a href="ruta.jsp">Rutas</a>

                        <a href="index" class="link--active">Cerrar Sesión</a>
                    </div>
                </div>
            </nav>
        </header>

        <section class="services-pago">

            <H1 class="card__title--center" id="pasajeroId" id-data="<%= pas.getId()%>">Selecciona tu Tarjeta a recargar</H1 >  

            <select name="tarjetaId" id="tarjetaId" class="dato tarjetero" >
                <% if (Tarjetas != null) { %>
                <% for (Tarjeta tarjeta : Tarjetas) {%>
                <option id-saldo="<%=tarjeta.getSaldo()%>" value="<%= tarjeta.getId()%>_<%=tarjeta.getSaldo()%>"><%= tarjeta.getId() + " S/. " + tarjeta.getSaldo()%></option>
                <% } %>
                <% } else { %>
                <option value="">No hay tarjetas disponibles.</option>
                <% }%>
            </select>
             <input type="text" name="dni" id="montoRecarga" placeholder="Cantidad a recargar (S/.) Nuevos Soles" class="dato-pago-ext" required
                           min="10" max="90" pattern="\d+(\.\d{1,2})?" title="Por favor, ingresa un monto válido (entre 10.00 y 90.00 soles">
            <div class="metodos-contenedor">

                <div class="metodo">
                    <h2 class="card__title--center">Efectivo</h2>

                    <div class="separador"></div>
                    <p class="dato-pago-txt">inserta efectivo</p>  

                    <box-icon size="cssSize" class="icon"  name='money'></box-icon>
                    

                   
                    <input type="submit" value="Recargar" class="button" id="miBoton" id-data="TP-002">


                </div>
                <div class="metodo">
                    <h2 class="card__title--center">Crédito/Débito</h2>
                    <div class="separador"></div>
                    <p class="dato-pago-txt">Rellena todos los datos</p>
                    <box-icon size="cssSize" class = "icon" name='credit-card-alt' type='solid' ></box-icon>

                    </select>
                    <input type="text"  id="nroTarjeta" placeholder="Numero de Tarjeta (Debito/Credito)" class="dato-pago" required
                           min="10" max="90" pattern="\d+(\.\d{1,2})?" title="Por favor, ingresa un monto válido (entre 10.00 y 90.00 soles">
                    <div class="dato-pago-agrupado">
                        <input type="text" id="vencimiento" placeholder="MM/AA" class="dato-pago-alter" required>
                        <input type="text"  id="cvv" placeholder="CVV" class="dato-pago-alter" required>

                    </div>
                    <input type="submit" value="Recargar" class="button" id="miBoton1" id-data="TP-001">



                </div>
                <div class="metodo">
                    <h2 class="card__title--center">Yape </h2>
                    <div class="separador"></div>
                                        <p class="dato-pago-txt">Escanea el QR</p>
                    <div class="qrs">
                        <div class="qr">

                            <box-icon  size="cssSize" class="icon"  name='qr' ></box-icon>
                        </div>




                    </div>



                    
                          
                    <input type="submit" value="Recargar" class="button"  id="miBoton2" id-data="TP-001" >


                </div>

            </div>



        </section>
    </body>
</html>

