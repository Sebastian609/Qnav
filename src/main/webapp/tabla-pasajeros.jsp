<%-- 
    Document   : recarga
    Created on : 5 oct. 2023, 22:39:40
    Author     : sebav
--%>

<%@page import="ModeloDAO.PasajeroDAO"%>
<%@page import="Modelo.Administrador"%>
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
        <!-- SweetAlert CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <!-- SweetAlert JS -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

        <link rel="stylesheet" href="style/normalize.css">
        <link rel="stylesheet" href="style/style.css">



    </head>
    <body>
        <%
            PasajeroDAO dao = new PasajeroDAO();
            List<Pasajero> pasajeros = dao.listar();

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
                        <a href="index.jsp" class="link--active">Cerrar Sesión</a>
                    </div>
                </div>
            </nav>
        </header>

        <section class="services">
            <div class="container">
                <h2>Listado de Pasajeros <%= pasajeros.size()%> </h2>
                <div class="listado">

                    <table class="pasajeros-table">
                        <tr>
                            <th>Documento</th>
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>correo</th>
                            <th>Contraseña</th>
                            <th>Fecha Nacimiento</th>
                            <th>Fecha Registro</th>
                            <th>Accion</th>
                        </tr>
                        <% for (Pasajero pasajero : pasajeros) {%>
                        <tr>
                            <td><%= pasajero.getNroDoc()%></td>
                            <td><%= pasajero.getNombres()%></td>
                            <td><%= pasajero.getApellidoPaterno()%></td>
                            <td><%= pasajero.getApellidoMaterno()%></td>
                            <td><%= pasajero.getCorreo()%></td>
                            <td><%= pasajero.getContraseña()%></td>
                            <td><%= pasajero.getFechaNacimiento().toString() %></td>
                            <td><%= pasajero.getFechaRegistro()%></td>

                            <td>

                                <a  onclick="editarPasajero('<%= pasajero.getNroDoc()%>', '<%= pasajero.getNombres()%>', '<%= pasajero.getApellidoPaterno()%>','<%= pasajero.getApellidoMaterno() %>','<%= pasajero.getCorreo()%>', '<%= pasajero.getContraseña()%>', '<%= pasajero.getFechaNacimiento()%>', '<%= pasajero.getFechaRegistro()%>', '<%= pasajero.getTelefono()%>')">Editar</a>


                                    <a href="ControladorCRUD?accion=borrar&id=<%= pasajero.getNroDoc()%>">Borrar</a>
                            </td>
                        </tr>
                        <% }%>
                    </table>


                </div>
                <!-- Formulario emergente -->

                <script>
                    function editarPasajero(nroDoc, nombres, apellidoPaterno, apellidoMaterno, correo, contrasena, fechaNacimiento, fechaRegistro, telefono) {
                    Swal.fire({
                    title: 'Editar Pasajero',
                            html:
                            `<input type="text" id="nuevoNroDoc" class="swal2-input" value="` + nroDoc + `" placeholder="Nuevo Documento">
            <input type="text" id="nuevosNombres" class="swal2-input" value="` + nombres + `" placeholder="Nuevos Nombres">
            <input type="text" id="nuevoApellidoPaterno" class="swal2-input" value="` + apellidoPaterno + `" placeholder="Nuevo Apellido Paterno">
            <input type="text" id="nuevoApellidoMaterno" class="swal2-input" value="` + apellidoMaterno + `" placeholder="Nuevo Apellido Materno">
            <input type="text" id="nuevoCorreo" class="swal2-input" value="` + correo + `" placeholder="Nuevo Correo">
            <input type="text" id="nuevaContrasena" class="swal2-input" value="`+contrasena+`" placeholder="Nueva Contraseña">
            <input type="text" id="nuevaFechaNacimiento" class="swal2-input" value="` + fechaNacimiento + `" placeholder="Nueva Fecha de Nacimiento">
            <input type="text" id="nuevaFechaRegistro" class="swal2-input" value="` + fechaRegistro + `" placeholder="Nueva Fecha de Registro">
                            <input type="text" id="nuevoTelefono" class="swal2-input" value="` + telefono + `" placeholder="Nuevo Teléfono">`,

                            showCancelButton: true,
                            confirmButtonText: 'Guardar',
                            cancelButtonText: 'Cancelar',
                            preConfirm: () => {
                     const nuevoNroDoc = Swal.getPopup().querySelector('#nuevoNroDoc').value;
                     const nuevaContrasena = Swal.getPopup().querySelector('#nuevaContrasena').value;
                const nuevosNombres = Swal.getPopup().querySelector('#nuevosNombres').value;
                const nuevoApellidoPaterno = Swal.getPopup().querySelector('#nuevoApellidoPaterno').value;
                const nuevoApellidoMaterno = Swal.getPopup().querySelector('#nuevoApellidoMaterno').value;
                const nuevoCorreo = Swal.getPopup().querySelector('#nuevoCorreo').value;
                
                const nuevaFechaNacimiento = Swal.getPopup().querySelector('#nuevaFechaNacimiento').value;
                const nuevaFechaRegistro = Swal.getPopup().querySelector('#nuevaFechaRegistro').value;
                const nuevoTelefono = Swal.getPopup().querySelector('#nuevoTelefono').value;
                
                    // Redirigir al servlet de edición con los nuevos datos
                    const urlRedireccion = `ControladorCRUD?accion=editar&id=`+nroDoc+`&nuevoNroDoc=`+nuevoNroDoc+`&nuevosNombres=`+nuevosNombres+`&nuevoApellidoPaterno=`+nuevoApellidoPaterno+`&nuevoApellidoMaterno=`+nuevoApellidoMaterno+`&nuevoCorreo=`+nuevoCorreo+`&nuevaContraseña=`+nuevaContrasena+`&nuevaFechaNacimiento=`+nuevaFechaNacimiento+`&nuevaFechaRegistro=`+nuevaFechaRegistro+`&nuevoTelefono=`+nuevoTelefono;

                // Redirigir al servlet de edición
                window.location.href = urlRedireccion;;
                    }
                    });
                    }

                </script>


            </div>
        </section>    </body>
</html>

