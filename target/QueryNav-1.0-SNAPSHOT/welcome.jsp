<%-- 
    Document   : welcome
    Created on : 1 nov 2023, 15:39:46
    Author     : sebav
--%>

<%@page import="Modelo.Pasajero"%>
<%@page import="Modelo.Bus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List" %>
<%-- Other imports --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            session = request.getSession();
            Pasajero user = (Pasajero) session.getAttribute("user");
            List<Bus> buses = (List<Bus>) session.getAttribute("buses");
        %>
        
        <h1><%= user.getNombres() %></h1>
        <h2>Buses disponibles</h2>
        <% for(Bus bus : buses) { %>
            <p>Bus ID: <%= bus.getId() %>, Placa: <%= bus.getPlaca() %>, Ruta : <%= bus.getRuta().getNombre()%></p>
            <!-- Add more information to display about the bus as needed -->
        <% } %>
    </body>
</html>
