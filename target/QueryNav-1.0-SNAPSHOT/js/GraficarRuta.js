var mapa;
var icon;
var greenIcon = new L.Icon({
    iconUrl:'img/bxs-map-inicio.svg',
   iconSize: [50,50],   
     iconAnchor: [25, 41],
    popupAnchor: [0, -50],
    shadowSize: [41, 41]
});

var redIcon = new L.Icon({
    iconUrl:'img/bxs-map-fin.svg',
    iconSize: [50,50],   
     iconAnchor: [25, 41],
    popupAnchor: [0, -50],
    shadowSize: [41, 41]
});

var purpleIcon = new L.Icon({
    iconUrl: 'img/bxs-bus-school.svg',
    iconSize: [25, 41],
     iconAnchor: [25, 41],
    popupAnchor: [-12, -50],
    shadowSize: [41, 41]
});

$(document).ready(function () {
    // Inicializar el mapa en el contenedor con id "map"
    mapa = L.map('map').setView([-16.42211, -71.47582], 14); // Coordenadas del centro del mapa y nivel de zoom

    // Agregar una capa de mapa base de OpenStreetMap
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(mapa);

    // Ejemplo de marcador en una ubicación específica
    var marker = L.marker([-16.42211, -71.47582]).addTo(mapa);
    marker.bindPopup("¡Hola! Soy un marcador.").openPopup();
    });

$('.dato-bus').on('click', function () {
    var spl = $(this).attr('id-data');
    var parts = spl.split('_');
    var rutaId = parts[0];

    $.ajax({
        url: 'ControladorGraficadorRutas?id=' + rutaId,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log('recibiendo coordenadas.....');
            var c = 0;
            var totalItems = data.length;

            // Limpiar el mapa antes de agregar nuevos marcadores y rutas
            mapa.eachLayer(function (layer) {
                if (layer instanceof L.Marker || layer instanceof L.Polyline) {
                    mapa.removeLayer(layer);
                }
            });

            var latlngs = [];

            $.each(data, function (index, value) {
                var coordenadas = value.split(',');

                if (c === 0) {
                    icon = greenIcon;
                    mapa.setView([coordenadas[0], coordenadas[1]], 14);
                } else {
                    if (index === totalItems - 1) {
                    icon = redIcon;
                    // Aquí puedes realizar acciones específicas para la última iteración
                }else{
                    icon = purpleIcon;
                    // Conectar los puntos con líneas poligonales
                }
                }
                latlngs.push([coordenadas[0], coordenadas[1]]);
                var marker = L.marker([coordenadas[0], coordenadas[1]], {icon: icon}).addTo(mapa);
                marker.bindPopup(coordenadas[2]).openPopup();

                c++;
            });

            // Agregar líneas poligonales
            if (latlngs.length > 1) {
                var polyline = L.polyline(latlngs, {color: 'purple'}).addTo(mapa);
            }
        },
        error: function (xhr, status, error) {
            console.log('Error al obtener las coordenadas de los paraderos.');
            console.log('Status: ' + status);
            console.log('Error: ' + error);
        }
    });
});
