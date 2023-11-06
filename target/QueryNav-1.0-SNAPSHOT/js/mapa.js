function onPageLoad() {
            var mapa;

            // Inicializa el mapa Leaflet en el contenedor 'map' con una vista centrada en las coordenadas proporcionadas
            // y un nivel de zoom predefinido
            mapa = L.map('map').setView([-16.42211, -71.47582], 14); // Coordenadas del centro del mapa y nivel de zoom

            // Crea una capa de mapa base de OpenStreetMap
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(mapa);
        }

        // Vincula la función onPageLoad al evento DOMContentLoaded para que se ejecute cuando la página esté lista
        document.addEventListener('DOMContentLoaded', onPageLoad);111