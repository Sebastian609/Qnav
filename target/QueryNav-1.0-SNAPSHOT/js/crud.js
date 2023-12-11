$(document).ready(function ()
{

    var controlador = "";
    //varables
    var datosPorPagina = 9;
    var cantidadPaginasTotales = 0;
    var paginaActual = 0;
    var mostrarInactivos = false;
    var buscar = "";
    var buscarPor = "ID";
    var tipoUsuario = $("#tipoUsuario").val();
    var idUsuarioPorEditar = "";
    var accion = "";

    //funciones que se ejecutan apenas abre el navegador

    cargarDatos(tipoUsuario);


    $(document).ready(function () {
        $(document).on('click', '#editar', function () {

            idUsuarioPorEditar = $(this).data('iduser');
            if (idUsuarioPorEditar !== "") {

                armarFormulario(tipoUsuario, 'editar');
            }
        });
    });
    $(document).on('click', '#enviarEditar', function ()
    {
        accion = "editar";
    });

    $(document).on('click', '#agregar', function ()
    {
        accion = "agregar";
        armarFormulario(tipoUsuario, accion);
    });


    $('#tipoUsuario').on('change', function ()
    {
        paginaActual = 0;
        accion = "agregar";
        armarFormulario(tipoUsuario, accion);
        tipoUsuario = $("#tipoUsuario").val();
        cargarDatos(tipoUsuario);
    });





    function armarFormulario(tipoUsuario, accion)
    {

        tipoUsuario = $("#tipoUsuario").val();
        $("#campos").empty();


        var campos;

        campos = '<input type="text" name="nombre" id="nombre" placeholder="Nombres" required class="form-control mb-3">' +
                '<input type="text" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido paterno" required class="dato-alter form-control mb-3">' +
                '<input type="text" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido materno" required class="dato-alter form-control mb-3">' +
                '<input type="email" name="correo" id="correo" placeholder="Correo" class="dato form-control mb-3" required>' +
                '<input type="text" name="dni" id="dni" placeholder="DNI" pattern="[0-9]{8}" title="Por favor ingrese un dni valido" required class="dato form-control mb-3">' +
                '<input type="date" name="fechaNacimiento" id="fechaNacimiento" placeholder="Fecha de Nacimiento (YYYY-MM-DD)" required class="dato form-control mb-3">' +
                '<input type="tel" name="telefono" id="telefono" placeholder="Número telefónico (9 dígitos)" pattern="[0-9]{9}" required class="dato form-control mb-3">' +
                '<input type="password" name="contrasena" id="contrasena" placeholder="Contraseña" required class="dato form-control mb-3">';


        switch (tipoUsuario) {
            case "pasajero":
                switch (accion)
                {
                    case 'agregar':
                        campos += '<button type="submit" id="enviarAñadir"  data-tipoUsuario="pasajero" class="btn btn-primary">Enviar</button>';

                        break;
                    case 'editar':

                        var filaUsuario = $(`#miTabla .id${idUsuarioPorEditar}`).closest('tr');

                        // Ahora puedes acceder a los elementos de la filaUsuario según sea necesario
                        var id = filaUsuario.find('td:eq(0)').text();
                        var nombre = filaUsuario.find('td:eq(1)').text();
                        var apellidoPaterno = filaUsuario.find('td:eq(2)').text();
                        var apellidoMaterno = filaUsuario.find('td:eq(3)').text();
                        var dni = filaUsuario.find('td:eq(4)').text();
                        var correo = filaUsuario.find('td:eq(5)').text();
                        var telefono = filaUsuario.find('td:eq(6)').text();
                        var fechaNacimiento = filaUsuario.find('td:eq(7)').text();

                        var contrasena = filaUsuario.find('td:eq(9)').text();
                        var estado = filaUsuario.find('td:eq(10)').text();


                        campos = "";
                        campos +=
                                '<input type="text" name="id" id="id" required class="form-control mb-3" value=' + id + ' readonly>' +
                                '<input type="text" name="nombre" id="nombre" placeholder="Nombres" required class="form-control mb-3" value=' + nombre + ' >' +
                                '<input type="text" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido paterno" required class="dato-alter form-control mb-3" value=' + apellidoPaterno + '>' +
                                '<input type="text" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido materno" required class="dato-alter form-control mb-3" value=' + apellidoMaterno + '>' +
                                '<input type="email" name="correo" id="correo" placeholder="Correo" class="dato form-control mb-3" required value=' + correo + '>' +
                                '<input type="text" name="dni" id="dni" placeholder="DNI" pattern="[0-9]{8}" title="Por favor ingrese un dni valido" required class="dato form-control mb-3"value=' + dni + '>' +
                                '<input type="date" name="fechaNacimiento" id="fechaNacimiento" placeholder="Fecha de Nacimiento (YYYY-MM-DD)" required class="dato form-control mb-3"value=' + fechaNacimiento + '>' +
                                '<input type="tel" name="telefono" id="telefono" placeholder="Número telefónico (9 dígitos)" pattern="[0-9]{9}" required class="dato form-control mb-3" value=' + telefono + '>' +
                                '<input type="text" name="contrasena" id="contrasena" placeholder="Contraseña" required class="dato form-control mb-3" value=' + contrasena + '>';
                        if (estado === "Activo")
                        {
                            campos += '<select id="estado"  name="estado" class="form-control mb-3">' +
                                    '<option value="activo"selected>Activo</option>' +
                                    '<option value="inactivo">Inactivo</option></select>' +
                                    '<button type="submit" id="enviarEditar" data-iduser="' + idUsuarioPorEditar + '" data-tipoUsuario="pasajero" class="btn btn-primary">Enviar</button>';
                        } else
                        {
                            campos += '<select id="estado" name="estado"class="form-control mb-3">' +
                                    '<option value="activo">Activo</option>' +
                                    '<option value="inactivo" selected>Inactivo</option></select>' +
                                    '<button type="submit" id="enviarEditar" data-iduser="' + idUsuarioPorEditar + '" data-tipoUsuario="pasajero" class="btn btn-primary">Enviar</button>';


                        }


                        break;

                    default:
                        break;
                }

                break;

            case "administrador":
                switch (accion) {
                    case "agregar":
                        campos += '<button type="submit" id="enviarAñadir"  data-tipoUsuario="administrador" class="btn btn-primary">Enviar</button>';
                        break;

                    default:

                        break;
                }
                break;

            default:
                break;
        }

        // Agrega los campos al contenedor #campos
        $("#campos").append(campos);

        $('#estado').prop('checked', true);
    }

    $("#campos").submit(function (event)
    {

        alert("submit"+ accion);
        event.preventDefault();
        var user = $("#tipoUsuario").val();
        var formData = $(this).serialize();

        switch (accion) {
            case "agregar":
                alert("agregando");
                console.log("añadiendo");
                añadir(user, formData);
                 
                break;
            case 'editar':

                editar(user, formData, idUsuarioPorEditar);
                location.reload();
                break;

            default:
                break;
        }

    });

    $(document).on('click', '#botonBuscar', function ()
    {
        armarFormulario(tipoUsuario);
    });
    $(document).ready(function () {
        $("#buscarPor").on("change", function ()
        {
            buscarPor = $(this).val();
        });
    });
    $(document).on('click', "#botonBuscar", function ()
    {
        buscar = $("#buscar").val().toUpperCase().replace(/\s/g, "");
        paginaActual = 0;

        if (buscar === "")
        {
            Swal.fire({
                icon: 'error',
                title: '¡Olvidate algo!',
                text: 'No has ingresado qué buscar.',

            });

        }
        cargarDatos('pasajero');
    });

    function cantidadPaginas(datosPorPagina, cantidadDatos)
    {
        if (datosPorPagina <= 0 || cantidadDatos < 0)
        {
            // Manejo de valores no válidos
            return "Error: Valores no válidos";
        }
        var paginasTotales = Math.ceil(cantidadDatos / datosPorPagina);
        return paginasTotales;
    }

    function añadirBotonesPaginacion(cantidad)
    {
        $("#paginacion").empty();
        var enlace;
        for (var i = 0; i <= cantidad - 1; i++)
        {

            if (i === paginaActual)
            {
                enlace = $('<li class="page-item active"><a class="page-link pagina" href="#" id="pagina" data-pagina="' + i + '">' + (i + 1) + '</a></li>');

            } else
            {
                enlace = $('<li class="page-item"><a class="page-link pagina" href="#" id="pagina" data-pagina="' + i + '">' + (i + 1) + '</a></li>');

            }
            $("#paginacion").append(enlace);


        }
    }

    function crearCabeceras(tipoUser)
    {
        $("#cabecera").empty();
        var cabecera = "<th>ID</th>\n\
        +<th>Nombres</th>+\n\
        <th>Apellido Paterno</th>+\n\
        <th>Apellido Materno</th>+\n\
        <th>DNI</th>+\n\
        <th>Correo</th>+\n\
        <th>Teléfono</th>+\n\
        <th>Fecha Nacimiento</th>+\n\
        <th>Fecha Registro</th>+\n\
        <th>Contraseña</th>+\n\
        <th>Estado</th>";
        switch (tipoUser) {
            case "pasajero":

                break;

            default:

                break;
        }
        cabecera += "<th>Acción</th>";
        $("#cabecera").append(cabecera);
    }

    $(document).on("click", ".pagina", function () {
        var pagina = $(this).data("pagina");
        paginaActual = pagina;
        cargarDatos(tipoUsuario);

    });



    $(document).ready(function ()
    {
        $("#estado").on("change", function ()
        {
            // Obtener el valor seleccionado del combo box
            var est = $(this).val();
            if (est === "si")
            {
                mostrarInactivos = true;
            } else
            {
                mostrarInactivos = false;
            }
            console.log(est);
            paginaActual = 0;

            cargarDatos(tipoUsuario);

        });
    });



    function cargarDatos(tipoUser) {
        crearCabeceras(tipoUser);
        var url = "";
        switch (tipoUser) {
            case "pasajero":
                url = 'ControladorCRUD?tipoUsuario=' + tipoUser + '&accion=listar';
                break
            case "administrador":
                url = 'ControladorCrudAdministrador?accion=listar';
                break;

            default:

                break;
        }


        $.ajax({
            type: 'GET',
            url: url,
            dataType: 'json',
            success: function (data)
            {

                filtrar(data);

            },
            error: function (error) {
                // Manejar errores
                Swal.fire("Error", "Ha ocurrido un error ", "error");
                var datosPasajeros = $("#datosPasajeros");
                datosPasajeros.empty();
                console.log(error);
            }
        });
    }

    // Cargar datos en la página inicial



    function filtrar(data) {
        var contador = 0;
        console.log("éxito con " + data.length);
        var listaFiltrada = [];

        $.each(data, function (index, user) {
            if (mostrarInactivos || user.estado) {
                if (buscar !== "") {
                    console.log("buscando por " + buscarPor);
                    switch (buscarPor) {
                        case "ID":
                            atributo = user.id;
                            break;

                        case "DNI":
                            atributo = user.nroDoc;
                            break;

                        case "Nombres":
                            atributo = user.nombres;
                            break;

                        case "ApellidoPaterno":
                            atributo = user.apellidoPaterno;
                            break;

                        case "ApellidoMaterno":
                            atributo = user.apellidoMaterno;
                            break;
                        case "Telefono":
                            atributo = user.telefono;
                            break;


                        default:
                            break;
                    }
                    console.log("comparando " + atributo + " y " + buscar);

                    if (buscar == atributo) {
                        contador++;
                        listaFiltrada.push(user);
                        console.log("hallado");
                    }
                } else {
                    contador++;
                    listaFiltrada.push(user);
                    console.log("barra de buscar vacía");
                }
            }


        });

        cantidadPaginasTotales = cantidadPaginas(datosPorPagina, contador);

        // Agrega datos a la tabla
        var rangoMenor = (datosPorPagina * paginaActual);
        var rangoMayor = rangoMenor + datosPorPagina;

        paginaActual = data.paginaActual;
        añadirBotonesPaginacion(cantidadPaginasTotales);
        console.log(listaFiltrada.length + " armando tabla con estos elementos");
        armarLista(listaFiltrada, rangoMenor, rangoMayor);
    }


    function armarLista(data, rangoMenor, rangoMayor) {
        console.log("armando lista....  " + data.length);
        var datosPasajeros = $("#datosPasajeros");
        var paginacion = $("#paginacion");

        datosPasajeros.empty();

        data.slice(rangoMenor, rangoMayor).forEach(function (user) {
            var fechaNacimiento = new Date(user.fechaNacimiento);
            var fechaRegistro = new Date(user.fechaRegistro);

            var formatoFecha = function (fecha) {
                var dia = fecha.getDate();
                var mes = fecha.getMonth() + 1;
                var año = fecha.getFullYear();

                if (mes < 10)
                {
                    mes = "0" + mes;
                }
                if (dia < 10)
                {
                    dia = "0" + dia;
                }
                return `${año}-${mes}-${dia}`;
            };

            var estado = user.estado ? "Activo" : "Inactivo";
            var fechaNacimientoFormateada = formatoFecha(fechaNacimiento);
            var fechaRegistroFormateada = formatoFecha(fechaRegistro);


            var fila = `<tr>
                        <td class="id${user.id}">${user.id}</td>
                        <td>${user.nombres}</td>
                        <td>${user.apellidoPaterno}</td>
                        <td>${user.apellidoMaterno}</td>
                        <td>${user.nroDoc}</td>
                        <td>${user.correo}</td>
                        <td>${user.telefono}</td>
                        <td>${fechaNacimientoFormateada}</td>
                        <td>${fechaRegistroFormateada}</td>
                        <td>${user.contraseña}</td>
                        <td>${estado}</td>
                        <td> <button type='button' class='btn btn-primary mb-3' data-toggle='modal' data-target='#formularioModal' id='editar' data-idUser='${user.id}' >Editar</button></td>+
                    </tr>`;

            datosPasajeros.append(fila);

        });
        console.log(datosPasajeros.length);
        paginacion.empty();
        paginaActual = data.paginaActual;
        añadirBotonesPaginacion(cantidadPaginasTotales);
    }

    function añadir(user, formData)
    {

        var url = "";
        switch (tipoUsuario) {
            case "pasajero":
                url = 'ControladorCRUD?tipoUsuario=' + user + '&accion=agregar';
                break;
            case "administrador":
                 url = 'ControladorCrudAdministrador?accion=agregar';
                break;

            default:

                break;
        }

        $.ajax({
            type: "GET",
            url: url,
            data: formData,

            success: function (response)
            {
                console.log(response);


                switch (response) {

                    case "0":
                        Swal.fire({
                            icon: 'Error',
                            title: 'Error',
                            text: 'DNI o correo clonados'
                        });
                        break;

                    case "1":
                        Swal.fire({
                            icon: 'success',
                            title: 'Guardado',
                            text: 'Usuario guardado con éxito.'
                        });
                        break;

                    case "2":
                        Swal.fire({
                            icon: 'Error',
                            title: 'Error',
                            text: 'Ya hay un usuario con esos datos.'
                        });
                        break;

                    default:

                        break;
                }




            },
            error: function () {
                Swal.fire({
                    icon: 'Error',
                    title: 'Error',
                    text: 'hubo un error interno'
                });
            }
        });
        setTimeout(function ()
                {
                   location.reload(); // Redirigir a otra página después de 3 segundos
                }, 3000);
    }


    function editar(user, formData, id)
    {
        $.ajax({
            type: "GET",
            url: 'ControladorCRUD?tipoUsuario=' + user + '&accion=editar&id=' + id,
            data: formData,

            success: function (response)
            {



                switch (response) {

                    case "0":
                        Swal.fire({
                            icon: 'Error',
                            title: 'Error',
                            text: 'DNI o correo clonados'
                        });
                        break;

                    case "1":
                        Swal.fire({
                            icon: 'success',
                            title: 'Guardado',
                            text: 'Datos actualizados con éxito.'
                        });
                        break;

                    case "3":
                        Swal.fire({
                            icon: 'Error',
                            title: 'Error',
                            text: 'Ocurrio un error interno'
                        });
                        break;

                    default:

                        break;
                }




            },
            error: function () {
                Swal.fire("Error", "Ha ocurrido un error al verificar el usuario", "error");
            }

        });
    }


});
