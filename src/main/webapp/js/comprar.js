document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('#miBoton').addEventListener('click', function () {
        realizarCompra('pasajeroId');
    });
    
    document.querySelector('#miBoton1').addEventListener('click', function () {
        validarDatosTarjeta('nroTarjeta', 'vencimiento', 'cvv');
    });
    
    document.querySelector('#miBoton2').addEventListener('click', function () {
        realizarCompra('pasajeroId');
    });
});

function realizarCompra(pasajeroId) {
   
   
    var pasajeroIdVal = document.querySelector('#' + pasajeroId).getAttribute('id-data');
    enviarDatosServidor(pasajeroIdVal);
}

function validarDatosTarjeta(tipoPago, nroTarjetaId, vencimientoId, cvvId) {
    var nroTarjeta = document.querySelector('#nroTarjeta').value;
    var vencimiento = document.querySelector('#vencimiento').value;
    var cvv = document.querySelector('#cvv').value;

    if (!validarNumeroTarjeta(nroTarjeta) || !validarVencimiento(vencimiento) || !validarCVV(cvv)) {
        mostrarError('Por favor, ingresa datos válidos para la tarjeta');
        return;
    }

    realizarCompra('pasajeroId');
}

function validarMontoRecarga(monto) {
    var expresion = /^\d+(\.\d{1,2})?$/;
    if (!expresion.test(monto) || parseFloat(monto) < 10 || parseFloat(monto) >= 100) {
        mostrarError('Por favor, ingresa un monto válido entre 10.00 y 99.00 soles (S/.)');
        return false;
    }
    return true;
}

function validarNumeroTarjeta(nroTarjeta) {
    var expresion = /^[0-9]{16}$/;
    return expresion.test(nroTarjeta);
}

function validarVencimiento(vencimiento) {
    var expresion = /^(0[1-9]|1[0-2])\/\d{2}$/;
    return expresion.test(vencimiento);
}

function validarCVV(cvv) {
    var expresion = /^[0-9]{3}$/;
    return expresion.test(cvv);
}

function mostrarError(mensaje) {
    Swal.fire({
        icon: 'error',
        title: '¡Error!',
        text: mensaje,
        confirmButtonColor: '#d33'
    });
}

function enviarDatosServidor(pasajeroId) {
    var datos = {
        
        pasajeroId: pasajeroId
        
    };

    var servletURL = 'ControladorCompras';

    fetch(servletURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Respuesta del servidor:', data);
        Swal.fire({
            icon: 'success',
            title: '¡Bien!',
            text: 'Recarga hecha con éxito',
            confirmButtonColor: 'green'
        });
        setTimeout(function () {
            location.reload();
        }, 1000);
    })
    .catch(error => {
        console.error('Error al enviar los datos:', error);
        Swal.fire({
            icon: 'error',
            title: '¡Error!',
            text: 'Ocurrió un error al enviar los datos al servidor',
            confirmButtonColor: '#d33'
        });
    });
}
