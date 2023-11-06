document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('#miBoton').addEventListener('click', function () {
        var tarjetaId_saldo = document.querySelector('#tarjetaId').value;
        var parts = tarjetaId_saldo.split('_');
        var tarjetaId = parts[0];
        var saldo = parts[1];

        var pasajeroId = document.querySelector('#pasajeroId').textContent;

        try {
            var ruta_bus_tarifa = document.querySelector('.dato-bus.selected').getAttribute('id-data');
            if (ruta_bus_tarifa === null || document.querySelector('.dato-bus.selected').classList.length === 1) {
                throw new Error('ruta_bus es nulo o no tiene la estructura deseada');
            }

            var parts2 = ruta_bus_tarifa.split('_');
            var rutaId = parts2[0];
            var busId = parts2[1];
            var tarifa = parts2[2];

            if (saldo < tarifa) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: '¡Saldo Insuficiente!',
                    confirmButtonColor: '#d33'
                });
                return;
            }

            Swal.fire({
                title: '¿Estás seguro de realizar el pago?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: 'green',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, realizar el pago',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Envío de datos al servlet usando AJAX
                    $.ajax({
                        type: 'POST',
                        url: 'ControladorConsumo', // Reemplazar con la URL real del servlet
                        data: JSON.stringify({
                            tarjetaId: tarjetaId,
                            rutaId: rutaId,
                            busId: busId,
                            pasajeroId: pasajeroId
                        }),
                        contentType: 'application/json',
                        success: function (data) {
                            if (data.message == "1")
                            {
                                Swal.fire('¡Pago realizado!', 'El pago ha sido exitoso', 'success');
                                setTimeout(function () {
                                    location.reload();
                                }, 2000);
                            } else
                            {
                                Swal.fire('Error', 'El pago no pudo realizarce', 'error');
                            }

                            // Lógica adicional después de realizar el pago si es necesario
                        },
                        error: function () {
                            Swal.fire('Error', 'Ocurrió un error al procesar la solicitud', 'error');
                        }
                    });
                } else {
                    Swal.fire('Cancelado', 'El pago ha sido cancelado', 'info');
                }
            });
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: '¡Error!',
                text: 'Selecciona un bus.',
                confirmButtonColor: '#d33'
            });
        }
    });
});
