document.getElementById('campoBusquedaEmpresa').addEventListener('input', function() {
    var input = this.value.toLowerCase();
    var empresas = document.querySelectorAll('.dato-scroll');

    empresas.forEach(function(empresa) {
        var nombreEmpresa = empresa.getAttribute('id');
        var busesEmpresa = document.querySelectorAll('.' + nombreEmpresa);

        if (nombreEmpresa.includes(input)) {
            empresa.style.display = 'block';
            busesEmpresa.forEach(function(bus) {
                bus.style.display = 'block';
            });
        } else {
            empresa.style.display = 'none';
            busesEmpresa.forEach(function(bus) {
                bus.style.display = 'none';
            });
        }
    });
}); 

document.addEventListener('DOMContentLoaded', function() {
    var buses = document.querySelectorAll('.dato-bus');

    buses.forEach(function(bus) {
        bus.addEventListener('click', function() {
            buses.forEach(function(b) {
                b.classList.remove('selected');
            });
            this.classList.add('selected');
        });
    });
});