/* global formData */
let formData;
$(document).ready(function () {
    $("#registroForm").submit(function (event) {
        event.preventDefault(); // Evitar el envío del formulario por defecto

        let fechaNacimiento = $("#fechaNacimiento").val();
        var edad = calcularEdad(fechaNacimiento);
 formData = $(this).serialize();
        var contrasena = $("#contrasena").val();
        var confirmarContrasena = $("#confirmarContrasena").val();

        if (edad < 6) {
            alert("Debes ser mayor de 6 años para registrarte.");
            return;
        }

        if (contrasena.length < 8 || contrasena.length > 12) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'La contraseña debe tener entre 8 y 12 caracteres'
            });
            return;
        }

        if (contrasena !== confirmarContrasena) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Las contraseñas no coinciden'
            });
            return;
        }

        $.ajax({
            type: "GET",
            url: "ControladorRegistro",
            data: formData,
            success: function (response) {
                console.log(response);
                if (response.error) {
                    Swal.fire("Acceso Denegado", response.error, "error");
                } else {
                    if (response !== "0") {
                        verifyCode(response); // Llama a la función para verificar el código
                    } else {
                        Swal.fire("Datos inválidos", "El correo y/o DNI ya se encuentran registrados", "error");
                    }
                }
            },
            error: function () {
                Swal.fire("Error", "Ha ocurrido un error al verificar los datos", "error");
            }
        });
    });
});

function calcularEdad(fechaNacimiento) {
    var hoy = new Date();
    var cumpleanos = new Date(fechaNacimiento);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var mes = hoy.getMonth() - cumpleanos.getMonth();

    if (mes < 0 || (mes === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }
    return edad;
}

function registrar() {
    $.ajax({
        type: "GET",
        url: "Registrar",
        data: formData,
        success: function (response) {
            console.log(response);
            if (response.error) {
                Swal.fire("Acceso Denegado", response.error, "error");
            } else {
                if (response === "1") {
                    Swal.fire('Código verificado', 'El código ingresado es correcto.', 'success');
                    setTimeout(function () {
                        window.location.href = "index.jsp";
                    }, 3000);
                }
            }
        },
        error: function () {
            Swal.fire("Error", "Ha ocurrido un error al verificar los datos", "error");
        }
    });
}

function verifyCode(response) {
    Swal.fire({
        title: 'Genial!',
        text: 'Inserte el código enviado a su correo',
        input: 'text',
        inputPlaceholder: 'Ingrese su código aquí',
        showCancelButton: false,
        confirmButtonText: 'Enviar',
        showLoaderOnConfirm: true,
        allowOutsideClick: false
    }).then((result) => {
        if (result.isConfirmed) {
            if (result.value === response) {
                registrar();
            } else {
                Swal.fire('Código incorrecto', 'Por favor, ingrese el código correcto.', 'error').then(() => {
                    verifyCode(response); // Llamada recursiva si el código es incorrecto
                });
            }
        }
    });
}
