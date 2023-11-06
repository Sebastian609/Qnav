$(document).ready(function () {
        $("#loginForm").submit(function (event) {
            event.preventDefault(); // Evitar el envío del formulario por defecto

            var formData = $(this).serialize(); // Serializar datos del formulario

            $.ajax({
                type: "GET",
                url: "Verificador",
                data: formData,
                success: function (response) {
                    console.log(response);
                    if (response == "1") {
                        Swal.fire("Acceso Concedido", "Bienvenido", "success");
                        setTimeout(function () {
                            window.location.href = "ruta.jsp"; // Redirigir a otra página después de 3 segundos
                        }, 3000);
                    } else {
                        Swal.fire("Acceso Denegado", "El Usuario no existe", "error");
                    }
                },
                error: function () {
                    Swal.fire("Error", "Ha ocurrido un error al verificar el usuario", "error");
                }
            });
        });
    });