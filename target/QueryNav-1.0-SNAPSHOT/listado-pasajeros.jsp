<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.List"%>
<%@ page import="Modelo.*"%>
<%@ page import="ModeloDAO.PasajeroDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Listado de Pasajeros</title>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script><link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>


        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Roboto', sans-serif;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
            }
            .table-title h2 {
                margin: 8px 0 0;
                font-size: 22px;
            }
            .search-box {
                position: relative;
                float: right;
            }
            .search-box input {
                height: 34px;
                border-radius: 20px;
                padding-left: 35px;
                border-color: #ddd;
                box-shadow: none;
            }
            .search-box input:focus {
                border-color: #3FBAE4;
            }
            .search-box i {
                color: #a0a5b1;
                position: absolute;
                font-size: 19px;
                top: 8px;
                left: 10px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child {
                width: 130px;
            }
            table.table td a {
                color: #a0a5b1;
                display: inline-block;
                margin: 0 5px;
            }
            table.table td a.view {
                color: #03A9F4;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #E34724;
            }
            table.table td i {
                font-size: 19px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 95%;
                width: 30px;
                height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 30px !important;
                text-align: center;
                padding: 0;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 6px;
                font-size: 95%;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>

    </head>
    <body>



        <div class="container-fluid mt-5 ">
            <h2>Registro de Usuarios</h2>
        </div>
        <div class="container-fluid mt-5 mb-2 text-center">
            <div class=" row align-items-center">
                <!-- Barra de búsqueda -->
                <div class="col-3 search-box">

                    <input id="buscar" class="form-control mr-2 " type="search" placeholder="Buscar" aria-label="Search">
                    <i class="material-icons">&#xE8B6;</i>
                </div>

                <!-- ComboBox (Select) y Botón de búsqueda en la misma columna -->
                <div class="col-1">

                    <select id="buscarPor"class="form-control">
                        <option value="ID" selected="">ID</option>
                        <option value="DNI">DNI</option>
                        <option value="Nombres">Nombres</option>
                        <option value="ApellidoPaterno">Apellido Paterno</option>
                        <option value="ApellidoMaterno">Apellido Materno</option>
                        <option value="Telefono">Teléfono</option>
                        <!-- Agrega más opciones según necesites -->
                    </select>


                </div>
                <div class="col-2">

                    <select id="estado" class="form-control">
                        <option value="si">Mostrar Inactivos</option>
                        <option value="no" selected> No Mostrar Inactivos</option>
                        
                        <!-- Agrega más opciones según necesites -->
                    </select>
                </div>

                <div class="col-1">
                    <button  id="botonBuscar" class="btn btn-primary" type="submit">Buscar</button>
                </div>
                <div class="col-1">
                    <p> Tipo Usuario </p>
                </div>
                <div class="col-2">
                    <select id="tipoUsuario" class="form-control">
                        <option value="pasajero">Pasajero</option>
                        <option value="transportista">Transportista</option>
                        <option value="empresa">Empresa</option>
                        <option value="administrador"selected>Administrador</option>
                    </select>
                </div>
                <div class="container- fluid col-2">

                    <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#formularioModal" id="agregar">+ Añadir un Usuario</button>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <table id="miTabla" class="table table-striped table-hover table-bordered">
                <thead>
                    <tr  id="cabecera">

                    </tr>
                </thead>
                <tbody id="datosPasajeros">
                    <!-- Aquí se agregarán las filas con datos -->
                </tbody>
            </table>
            <nav aria-label="Page navigation">
                <ul class="pagination" id="paginacion">
                    <!-- Aquí se agregarán los enlaces de paginación -->
                </ul>
            </nav>  
        </div>

        <div class="modal" id="formularioModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Mi Formulario</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">                      
                        <form id="campos">         
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>

                </div>
            </div>
        </div>
        
    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/crud.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    </body>
</html>
